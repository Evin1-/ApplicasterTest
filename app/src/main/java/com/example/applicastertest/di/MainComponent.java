package com.example.applicastertest.di;

import com.example.applicastertest.data.TweetsRepository;
import com.example.applicastertest.ui.search.SearchFragment;
import com.example.applicastertest.ui.search.SearchModule;

import dagger.Component;

/**
 * Created by user on 3/28/17.
 */

@Component(modules = {DataModule.class, SearchModule.class})
public interface MainComponent {
    void inject(SearchFragment searchFragment);
    void inject(TweetsRepository tweetsRepository);
}
