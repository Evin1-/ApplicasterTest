package com.example.applicastertest.data;

import com.example.applicastertest.App;
import com.example.applicastertest.data.entities.SearchTerm;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user on 3/29/17.
 */

public class TermsRepository {

    @Inject
    Realm realm;

    public TermsRepository(App app) {
        app.getMainComponent().inject(this);
    }

    public void loadLatestSearchTerms(Observer<List<SearchTerm>> observer) {
        RealmResults<SearchTerm> searchTerms = realm.where(SearchTerm.class).findAll();
        Observable.just(realm.copyFromRealm(searchTerms))
                .subscribe(observer);
    }
}
