package com.example.applicastertest.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.applicastertest.App;
import com.example.applicastertest.R;
import com.example.applicastertest.ui.input.InputFragment;
import com.example.applicastertest.ui.search.SearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        InputFragment.Callback {
    private static final String INPUT_FRAGMENT_TAG = "INPUT_FRAGMENT_TAG";
    private static final String SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG";

    // TODO: 3/28/17 Hide keys with NDK

    @BindView(R.id.a_main_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.a_main_nav)
    NavigationView navigationView;
    @BindView(R.id.a_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_main_txt)
    TextView textView;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDaggerComponent();

        ButterKnife.bind(this);

        setupToolbar();
        setupDrawer();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_camera) {

            } else if (id == R.id.nav_gallery) {

            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.m_search) {
            InputFragment inputFragment = new InputFragment();
            inputFragment.show(getSupportFragmentManager(), INPUT_FRAGMENT_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Snackbar.make(findViewById(R.id.a_main_frame), error, Snackbar.LENGTH_LONG).show();
    }

    private void setupDaggerComponent() {
        ((App) getApplication()).getMainComponent().inject(this);
    }

    @Override
    public void searchTermSelected(String searchTerm) {
        textView.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.a_main_frame, SearchFragment.newInstance(searchTerm), SEARCH_FRAGMENT_TAG)
                .commit();
    }
}
