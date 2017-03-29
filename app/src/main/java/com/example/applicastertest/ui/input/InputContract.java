package com.example.applicastertest.ui.input;

import com.example.applicastertest.ui.BasePresenter;
import com.example.applicastertest.ui.BaseView;

/**
 * Created by user on 3/29/17.
 */

public interface InputContract {
    interface View extends BaseView {
        void validData(String data);
    }

    interface Presenter extends BasePresenter<View> {
        void validateData(String data);
    }
}
