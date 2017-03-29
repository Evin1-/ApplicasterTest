package com.example.applicastertest.ui.search;

import android.util.Log;

import com.example.applicastertest.data.TweetsRepository;
import com.example.applicastertest.data.entities.TweetSearch;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by user on 3/28/17.
 */



public class SearchPresenter implements SearchContract.Presenter, Observer<List<TweetSearch>> {
    private static final String TAG = "SearchPresenterTAG_";

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
    public void onNext(List<TweetSearch> tweets) {
        if (tweets == null || tweets.size() == 0) {
            view.showError("No results found!");
        } else {
            view.updateTweets(tweets);
        }
    }

    @Override
    public void onError(Throwable e) {
        view.showError(e.toString());
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        view.hideProgress();
    }
}
