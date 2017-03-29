package com.example.applicastertest.data;

import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.List;

import io.reactivex.Observer;
import retrofit2.Call;

/**
 * Created by user on 3/28/17.
 */

public class TweetsRepository {
    private static final String TAG = "TweetsRepositoryTAG_";

    public void getTweets(String searchTerm, Observer<List<Tweet>> observer) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        SearchService searchService = twitterApiClient.getSearchService();

        Call<Search> tweetCall = searchService.tweets(searchTerm,
                null, null, null, null, null, null, null, null, null);

        tweetCall.enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                Log.d(TAG, "success: " + result.data.tweets);
                for (Tweet tweet : result.data.tweets) {
                    Log.d(TAG, "success: " + tweet.text);
                }
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }
}
