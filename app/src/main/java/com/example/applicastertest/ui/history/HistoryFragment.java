package com.example.applicastertest.ui.history;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.applicastertest.App;
import com.example.applicastertest.R;
import com.example.applicastertest.data.entities.SearchTerm;
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
public class HistoryFragment extends Fragment implements HistoryContract.View {

    private static final String TAG = "HistoryFragmentTAG_";

    @BindView(R.id.f_history_recycler)
    RecyclerView recyclerView;

    @Inject
    HistoryContract.Presenter presenter;

    private List<SearchTerm> searchTerms;
    private HistoryAdapter historyAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDaggerComponent();

        searchTerms = new ArrayList<>();
        historyAdapter = new HistoryAdapter(searchTerms);

        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(historyAdapter);
        recyclerView.addItemDecoration(new SimpleItemDecoration(10));

        presenter.loadTerms();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showProgress() {
        // TODO: 3/29/17
    }

    @Override
    public void hideProgress() {
        // TODO: 3/29/17
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
    public void updateSearchTerms(List<SearchTerm> searchTerms) {
        this.searchTerms.clear();
        this.searchTerms.addAll(searchTerms);
        historyAdapter.notifyDataSetChanged();
    }

    private void setupDaggerComponent() {
        ((App) getActivity().getApplication()).getMainComponent().inject(this);
    }
}
