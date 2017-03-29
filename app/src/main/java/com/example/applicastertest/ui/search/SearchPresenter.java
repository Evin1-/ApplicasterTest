package com.example.applicastertest.ui.search;

import com.example.applicastertest.data.TweetsRepository;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by user on 3/28/17.
 */

public class SearchPresenter implements SearchContract.Presenter, Observer<List<Tweet>> {
    private TweetsRepository tweetsRepository;
    private SearchContract.View view;

    private Disposable disposable;

    public SearchPresenter(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void loadTweets(String searchTerm) {
        view.showProgress();
        tweetsRepository.getTweets(searchTerm, this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(List<Tweet> value) {
        view.updateTweets(value);
    }

    @Override
    public void onError(Throwable e) {
        view.showError(e.toString());
    }

    @Override
    public void onComplete() {
        view.hideProgress();
    }
}
