package com.example.applicastertest.di;

import com.example.applicastertest.data.TweetsRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 3/28/17.
 */

@Module
public class DataModule {

    @Provides
    public TweetsRepository provideTweetsRepository() {
        return new TweetsRepository();
    }
}
