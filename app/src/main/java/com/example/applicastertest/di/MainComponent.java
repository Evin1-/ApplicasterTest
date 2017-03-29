package com.example.applicastertest.di;

import com.example.applicastertest.data.TermsRepository;
import com.example.applicastertest.data.TweetsRepository;
import com.example.applicastertest.ui.history.HistoryFragment;
import com.example.applicastertest.ui.history.HistoryModule;
import com.example.applicastertest.ui.input.InputFragment;
import com.example.applicastertest.ui.input.InputModule;
import com.example.applicastertest.ui.main.MainActivity;
import com.example.applicastertest.ui.main.MainModule;
import com.example.applicastertest.ui.search.SearchFragment;
import com.example.applicastertest.ui.search.SearchModule;

import dagger.Component;

/**
 * Created by user on 3/28/17.
 */

@Component(modules = {DataModule.class, SearchModule.class, MainModule.class,
        InputModule.class, HistoryModule.class})
public interface MainComponent {
    // TODO: 3/29/17 Setup sub-modules and custom scopes
    void inject(MainActivity mainActivity);

    void inject(SearchFragment searchFragment);

    void inject(InputFragment inputFragment);

    void inject(HistoryFragment historyFragment);

    void inject(TweetsRepository tweetsRepository);

    void inject(TermsRepository termsRepository);
}
