package com.example.applicastertest.ui;

/**
 * Created by user on 3/28/17.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
