package com.example.applicastertest.di;

import com.example.applicastertest.App;
import com.example.applicastertest.data.TweetsRepository;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
    public RealmConfiguration provideRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    @Provides
    public Realm provideRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }
}
