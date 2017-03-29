package com.example.applicastertest.ui.search;

import com.example.applicastertest.data.TweetsRepository;
import com.example.applicastertest.data.entities.TweetSearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by user on 3/29/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    private static final String TEST_STRING = "test";

    @Mock
    TweetsRepository tweetsRepository;

    @Mock
    SearchContract.View view;

    SearchPresenter presenter = new SearchPresenter(tweetsRepository);

    @Before
    public void setupTests() {
        presenter.attachView(view);
    }

    @Test
    public void canInstantiatePresenter() {
        assertNotNull(presenter);
    }

    @Test
    public void shouldLoadData() {
        presenter.attachView(view);
    }
}