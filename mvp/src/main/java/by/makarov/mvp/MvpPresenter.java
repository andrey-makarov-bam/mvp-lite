package by.makarov.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class MvpPresenter<V extends MvpView> implements Presenter<V>, LifecycleObserver {
    private CompositeDisposable visibleDisposables = new CompositeDisposable();
    private CompositeDisposable attachedDisposables = new CompositeDisposable();
    private WeakReference<V> weakReference;
    private boolean isFirstStart;

    public V getView() {
        return (weakReference != null) ? weakReference.get() : null;
    }

    @Override
    public void attachToLifecycle(V mvpView, Lifecycle lifecycle) {
        weakReference = new WeakReference<>(mvpView);
        lifecycle.addObserver(this);
    }

    /**
     * onFirstStart Called when your view is about to be first time start.
     */
    public void onFirstStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void init() {
        if (!isFirstStart) {
            onFirstStart();
            isFirstStart = true;
        }
    }

    /**
     * On view will show. Called when your view is about to be seen on the screen.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    public abstract void onViewWillShow();

    /**
     * On view will hide. Called when your view is about to hide from the screen.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    public void onViewWillHide() {
        visibleDisposables.clear();
        weakReference.clear();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Override
    public void onViewDetached() {
        attachedDisposables.clear();
        weakReference.clear();
    }

    /**
     * Dispose on view detach.
     *
     * @param disposable Disposable to be disposed of upon view detachment
     */
    protected void disposeOnViewDetach(@NonNull Disposable disposable) {
        attachedDisposables.add(disposable);
    }

    /**
     * Dispose on view will hide.
     *
     * @param disposable Disposable to be disposed of upon view will hide
     */
    protected void disposeOnViewWillHide(@NonNull Disposable disposable) {
        visibleDisposables.add(disposable);
    }

    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }
}
