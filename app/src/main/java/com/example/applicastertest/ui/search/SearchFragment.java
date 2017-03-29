package com.example.applicastertest.ui.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.applicastertest.App;
import com.example.applicastertest.R;
import com.example.applicastertest.data.entities.TweetSearch;
import com.example.applicastertest.util.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchContract.View {
    private static final String TAG = "SearchFragmentTAG_";
    private static final String SEARCH_TERM_KEY = "SEARCH_TERM_KEY";

    @Inject
    SearchContract.Presenter presenter;

    @BindView(R.id.f_search_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.f_search_recycler)
    RecyclerView recyclerView;

    private List<TweetSearch> tweets;
    private SearchAdapter searchAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String searchTerm) {
        Bundle args = new Bundle();
        args.putString(SEARCH_TERM_KEY, searchTerm);

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDaggerComponent();

        tweets = new ArrayList<>();
        searchAdapter = new SearchAdapter(tweets);

        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchAdapter);
        recyclerView.addItemDecoration(new SimpleItemDecoration(10));

        presenter.loadTweets(getArguments().getString(SEARCH_TERM_KEY));
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
    public void updateTweets(List<TweetSearch> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        searchAdapter.notifyDataSetChanged();
    }

    private void setupDaggerComponent() {
        ((App) getActivity().getApplication()).getMainComponent().inject(this);
    }
}
