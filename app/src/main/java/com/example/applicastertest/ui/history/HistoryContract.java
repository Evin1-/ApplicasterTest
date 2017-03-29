package com.example.applicastertest.ui.history;

import com.example.applicastertest.data.entities.SearchTerm;
import com.example.applicastertest.data.entities.TweetSearch;
import com.example.applicastertest.ui.BasePresenter;
import com.example.applicastertest.ui.BaseView;

import java.util.List;

/**
 * Created by user on 3/28/17.
 */

public interface HistoryContract {
    interface View extends BaseView {
        void updateSearchTerms(List<SearchTerm> searchTerms);
    }

    interface Presenter extends BasePresenter<View> {
        void loadTerms();
    }
}
