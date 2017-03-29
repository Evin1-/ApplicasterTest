package com.example.applicastertest.ui.input;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 3/29/17.
 */

@Module
public class InputModule {
    @Provides
    InputContract.Presenter providePresenter(){
        return new InputPresenter();
    }
}
