package com.example.applicastertest.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 3/29/17.
 */

@Module
public class MainModule {

    @Provides
    public MainContract.Presenter providePresenter() {
        return new MainPresenter();
    }
}
