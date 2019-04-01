package by.makarov.life.sample;

import android.util.Log;

import by.makarov.mvp.MvpPresenter;

public class MainPresenter extends MvpPresenter<MainView> {
    private final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void onFirstStart() {
        Log.d(TAG, "onFirstStart");
    }

    @Override
    public void onViewWillShow() {
        Log.d(TAG, "onViewWillShow");
        getView().test();
    }
}
