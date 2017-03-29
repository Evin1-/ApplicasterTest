package com.example.applicastertest.ui.main;

import com.example.applicastertest.ui.BasePresenter;
import com.example.applicastertest.ui.BaseView;

/**
 * Created by user on 3/28/17.
 */

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
