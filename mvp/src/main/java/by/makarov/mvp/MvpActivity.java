package by.makarov.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public abstract class MvpActivity<P extends MvpPresenter<MvpView>> extends AppCompatActivity implements MvpView, LifecycleOwner {
    private WeakReference<P> weakPresenter;

    protected void setPresenter(P presenter) {
        weakPresenter = new WeakReference<>(presenter);
    }

    public P getPresenter() {
        return weakPresenter.get();
    }

    @Override
    protected void onStart() {
        super.onStart();
        weakPresenter.get().attachToLifecycle(this, getLifecycle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weakPresenter.get().detachLifecycle(getLifecycle());
    }
}
