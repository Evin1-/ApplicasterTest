package com.example.applicastertest.di;

import com.example.applicastertest.App;
import com.example.applicastertest.data.TweetsRepository;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by user on 3/28/17.
 */

@Module
public class DataModule {

    private App app;

    public DataModule(App app) {
        this.app = app;
    }

    @Provides
    public TweetsRepository provideTweetsRepository() {
        return new TweetsRepository(app);
    }

    @Provides
    public Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
