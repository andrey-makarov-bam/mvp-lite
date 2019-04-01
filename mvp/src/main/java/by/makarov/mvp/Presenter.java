package by.makarov.mvp;

import android.arch.lifecycle.Lifecycle;

interface Presenter<V extends MvpView> {
    void attachToLifecycle(V mvpView, Lifecycle lifecycle);

    V getView();

    void onViewWillShow();

    void onViewWillHide();

    void onViewDetached();

    void detachLifecycle(Lifecycle lifecycle);
}
