package com.example.applicastertest.ui.search;

import com.example.applicastertest.data.TweetsRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 3/28/17.
 */

@Module
public class SearchModule {
    @Provides
    public SearchContract.Presenter providePresenter(TweetsRepository tweetsRepository) {
        return new SearchPresenter(tweetsRepository);
    }
}
