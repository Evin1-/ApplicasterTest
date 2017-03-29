package com.example.applicastertest.ui.input;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicastertest.App;
import com.example.applicastertest.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends DialogFragment implements InputContract.View {

    private Callback callback;

    @BindView(R.id.f_input_edit)
    EditText editText;
    @BindView(R.id.f_input_input)
    TextInputLayout textInputLayout;

    @Inject
    InputContract.Presenter presenter;

    public interface Callback {
        void searchTermSelected(String searchTerm);
    }

    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDaggerComponent();
        presenter.attachView(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) context;
        } catch (Exception e) {
            throw new ClassCastException("You should implement the Callback in your Activity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick(R.id.f_input_btn)
    public void searchClicked() {
        presenter.validateData(editText.getText().toString().trim());
    }

    @Override
    public void showProgress() {
        // TODO: 3/29/17
    }

    @Override
    public void hideProgress() {
        // TODO: 3/29/17
    }

    @Override
    public void showError(String error) {
        textInputLayout.setError(error);
    }

    @Override
    public void validData(String data) {
        callback.searchTermSelected(data);
        dismiss();
    }

    private void setupDaggerComponent() {
        ((App) getActivity().getApplication()).getMainComponent().inject(this);
    }
}
