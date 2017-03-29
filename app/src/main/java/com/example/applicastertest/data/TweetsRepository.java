package com.example.applicastertest.data;

import android.util.Log;

import com.example.applicastertest.App;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Call;

/**
 * Created by user on 3/28/17.
 */

public class TweetsRepository {
    private static final String TAG = "TweetsRepositoryTAG_";

    private static final String POPULAR_TAG = "popular";
    private static final Integer TWEET_LIMIT = 10;

    @Inject
    Realm realm;

    public TweetsRepository(App app) {
        setupDaggerComponent(app);
    }

    public void getTweets(String searchTerm, Observer<List<Tweet>> observer) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        SearchService searchService = twitterApiClient.getSearchService();

        Call<Search> tweetCall = searchService.tweets(searchTerm,
                null, null, null, POPULAR_TAG, TWEET_LIMIT, null, null, null, null);

        tweetCall.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                if (result.response.isSuccessful()) {
                    formatTweets(searchTerm, result.data.tweets, observer);
                }
            }

            @Override
            public void failure(TwitterException exception) {
                Observable.error(exception);
            }
        });
    }

    private void formatTweets(String searchTerm, List<Tweet> tweets, Observer<List<Tweet>> observer) {
        Observable.fromCallable(() -> orderTweets(tweets)) // Order tweets
                .doOnNext(tweets1 -> saveTweets(searchTerm, tweets1)) // Save tweets
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void saveTweets(String searchTerm, List<Tweet> tweets) {

    }

    private List<Tweet> orderTweets(List<Tweet> tweets) {
        List<Tweet> orderedTweets = new ArrayList<>(tweets);
        Collections.copy(orderedTweets, tweets);
        Collections.sort(orderedTweets, (tweet1, tweet2)
                -> Integer.valueOf(tweet2.user.followersCount).compareTo(tweet1.user.followersCount));
        return orderedTweets;
    }

    private void setupDaggerComponent(App app) {
        app.getMainComponent().inject(this);
    }
}
