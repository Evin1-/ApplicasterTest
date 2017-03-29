package com.example.applicastertest.ui.history;

import com.example.applicastertest.data.TermsRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 3/29/17.
 */

@Module
public class HistoryModule {
    @Provides
    public HistoryContract.Presenter providePresenter(TermsRepository termsRepository) {
        return new HistoryPresenter(termsRepository);
    }
}
