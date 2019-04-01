package by.makarov.life.sample;

import android.os.Bundle;
import by.makarov.mvp.MvpActivity;

public class MainActivity extends MvpActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPresenter(new MainPresenter());
    }

    @Override
    public void test() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachLifecycle(getLifecycle());
    }
}
