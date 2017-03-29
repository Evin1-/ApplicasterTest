package com.example.applicastertest;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.applicastertest.di.DaggerMainComponent;
import com.example.applicastertest.di.DataModule;
import com.example.applicastertest.di.MainComponent;
import com.example.applicastertest.ui.search.SearchModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by user on 3/28/17.
 */

public class App extends Application {

    private static final String TAG = "AppTAG_";
    private static final String TWITTER_KEY = "TWITTER_KEY";
    private static final String TWITTER_SECRET = "TWITTER_SECRET";

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeFabric();
        initializeRealm();

        mainComponent = DaggerMainComponent.builder()
                .dataModule(new DataModule(this))
                .searchModule(new SearchModule())
                .build();
    }

    private void initializeRealm() {
        Realm.init(getApplicationContext());
    }

    private void initializeFabric() {
        try {
            ApplicationInfo applicationInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;

            TwitterAuthConfig authConfig =
                    new TwitterAuthConfig(bundle.getString(TWITTER_KEY), bundle.getString(TWITTER_SECRET));
            Fabric.with(this, new Twitter(authConfig));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "onCreate: ", e);
        }
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
