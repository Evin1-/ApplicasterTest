package com.example.applicastertest.ui.input;

/**
 * Created by user on 3/29/17.
 */

public class InputPresenter implements InputContract.Presenter {

    private InputContract.View view;

    @Override
    public void attachView(InputContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void validateData(String data) {
        // TODO: 3/29/17 Finish validation

        if (!data.contains("#")) {
            view.showError(data + ": Search term should contain #: ");
        } else if (data.trim().length() == 0) {
            view.showError(data + ": Search term is empty");
        } else {
            view.validData(data);
        }
    }
}
