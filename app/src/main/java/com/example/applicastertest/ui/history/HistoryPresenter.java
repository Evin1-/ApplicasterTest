package com.example.applicastertest.ui.history;

import com.example.applicastertest.data.TermsRepository;
import com.example.applicastertest.data.entities.SearchTerm;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by user on 3/29/17.
 */

public class HistoryPresenter implements HistoryContract.Presenter, Observer<List<SearchTerm>> {

    private TermsRepository termsRepository;
    private HistoryContract.View view;

    private Disposable disposable;

    public HistoryPresenter(TermsRepository termsRepository) {
        this.termsRepository = termsRepository;
    }

    @Override
    public void attachView(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void loadTerms() {
        view.showProgress();
        termsRepository.loadLatestSearchTerms(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onNext(List<SearchTerm> searchTerms) {
        if (searchTerms == null || searchTerms.size() == 0) {
            view.showError("No previous history!");
        } else {
            view.updateSearchTerms(searchTerms);
        }
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
