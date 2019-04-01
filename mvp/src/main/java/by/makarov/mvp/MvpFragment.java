package by.makarov.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

public abstract class MvpFragment<P extends MvpPresenter<MvpView>> extends Fragment implements MvpView {
    private WeakReference<P> weakPresenter;

    protected void setPresenter(P presenter) {
        weakPresenter = new WeakReference<>(presenter);
    }

    public P getPresenter() {
        return weakPresenter.get();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weakPresenter.get().attachToLifecycle(this, getLifecycle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        weakPresenter.get().detachLifecycle(getLifecycle());
    }
}
