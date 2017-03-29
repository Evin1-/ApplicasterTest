package com.example.applicastertest.ui.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.applicastertest.App;
import com.example.applicastertest.R;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchContract.View {
    private static final String TAG = "SearchFragmentTAG_";

    @Inject
    SearchContract.Presenter presenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDaggerComponent();
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadTweets("#USAvPAN");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showProgress() {
        // TODO: 3/28/17 Show Progress Bar
    }

    @Override
    public void hideProgress() {
        // TODO: 3/28/17 Hide Progress Bar
    }

    @Override
    public void showError(String error) {
        if (getView() == null) {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        } else {
            Snackbar.make(getView(), error, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void updateTweets(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            Log.d(TAG, "updateTweets: " + tweet.user.followersCount + " " + tweet.text);
        }
    }

    private void setupDaggerComponent() {
        ((App) getActivity().getApplication()).getMainComponent().inject(this);
    }
}
