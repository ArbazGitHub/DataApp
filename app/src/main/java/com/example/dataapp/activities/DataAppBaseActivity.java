package com.example.dataapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dataapp.R;

public class DataAppBaseActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView tbIvHMenu, tbIVFilter, tbIVSearch, tbIvBack;
    TextView tbTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Toolbar setToolbar() {
        try {

            toolbar = findViewById(R.id.toolbar);

            tbTvTitle = toolbar.findViewById(R.id.tbTvTitle);
            tbIvHMenu = toolbar.findViewById(R.id.tbIvHMenu);
            tbIVFilter = toolbar.findViewById(R.id.tbIVFilter);
            tbIVSearch = toolbar.findViewById(R.id.tbIVSearch);
            tbIvBack = toolbar.findViewById(R.id.tbIvBack);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);


            return toolbar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setToolbarTitle(String title) {
        try {
            if (!title.equals(""))
                tbTvTitle.setText(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //display back
    public ImageView displayBack() {
        tbIvBack.setVisibility(View.VISIBLE);
        return tbIvBack;
    }

    //hide back
    public void hideBack() {
        tbIvBack.setVisibility(View.GONE);
    }

    //display filter
    public ImageView displayMenu() {
        tbIvHMenu.setVisibility(View.VISIBLE);
        return tbIvHMenu;
    }

    //hide filter
    public void hideMenu() {
        tbIvHMenu.setVisibility(View.GONE);
    }

    //display filter
    public ImageView displayFilter() {
        tbIVFilter.setVisibility(View.VISIBLE);
        return tbIVFilter;
    }

    //hide filter
    public void hideFilter() {
        tbIVFilter.setVisibility(View.GONE);
    }

    //display search
    public ImageView displaySearch() {
        tbIVSearch.setVisibility(View.VISIBLE);
        return tbIVSearch;
    }

    //hide search
    public void hideSearch() {
        tbIVSearch.setVisibility(View.GONE);
    }

}

