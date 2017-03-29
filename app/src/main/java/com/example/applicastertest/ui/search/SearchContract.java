package com.example.applicastertest.ui.search;

import com.example.applicastertest.data.entities.TweetSearch;
import com.example.applicastertest.ui.BasePresenter;
import com.example.applicastertest.ui.BaseView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by user on 3/28/17.
 */

public interface SearchContract {
    interface View extends BaseView {
        void updateTweets(List<TweetSearch> tweets);
    }

    interface Presenter extends BasePresenter<View> {
        void loadTweets(String searchTerm);
    }
}
