package com.example.dataapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dataapp.R;
import com.example.dataapp.adapters.home.LeftMenuAdapter;
import com.example.dataapp.fragments.AddCategoryFragment;
import com.example.dataapp.fragments.AddContactFragment;
import com.example.dataapp.fragments.ContactListFragment;
import com.example.dataapp.listeners.RecyclerItemClickListener;
import com.example.dataapp.models.home.LeftMenuMain;

import java.util.ArrayList;

public class HomeActivity extends DataAppBaseActivity implements View.OnClickListener {

    RecyclerView rvLeftMenu;
    LeftMenuAdapter leftMenuAdapter;
    LeftMenuMain leftMenuMain;
    ArrayList<LeftMenuMain> leftMenuMainArrayList = new ArrayList<>();
    Toolbar toolbar;
    ImageView tbIvHMenu, tbIVFilter, tbIVSearch;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {


            drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            //set Default Fragment Here
            ContactListFragment fragment = new ContactListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.AppContainer, fragment)
                    .commit();

            initViews();
            setListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void initViews() {
        try {
            setToolbar();
            setToolbarTitle(getString(R.string.menu_1));
            tbIvHMenu = displayMenu();
//            tbIVFilter = displayFilter();
//            tbIVSearch = displaySearch();

            leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.menu_1)));
            leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.menu_2)));
            leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.menu_3)));
            rvLeftMenu = findViewById(R.id.rvLeftMenu);
            rvLeftMenu.setLayoutManager(new LinearLayoutManager(this));

            leftMenuAdapter = new LeftMenuAdapter(leftMenuMainArrayList, this);
            rvLeftMenu.setAdapter(leftMenuAdapter);

            rvLeftMenu.addOnItemTouchListener(new RecyclerItemClickListener(this, rvLeftMenu,
                    new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            leftMenuMain = leftMenuMainArrayList.get(position);

                            try {
                                if (leftMenuMain.getStrMenuTitle().equals(getString(R.string.menu_1))) {
                                    setToolbarTitle(getString(R.string.menu_1));

                                    changeFragment(new ContactListFragment());
                                    DrawerLayout drawer_home = findViewById(R.id.drawer_layout);
                                    drawer_home.closeDrawer(GravityCompat.START);
                                } else if (leftMenuMain.getStrMenuTitle().equals(getString(R.string.menu_2))) {
                                    setToolbarTitle(getString(R.string.menu_2));
                                    hideFilter();
                                    hideSearch();
                                    changeFragment(new AddContactFragment());
                                    DrawerLayout drawer_home = findViewById(R.id.drawer_layout);
                                    drawer_home.closeDrawer(GravityCompat.START);
                                } else {
                                    setToolbarTitle(getString(R.string.menu_3));
                                    hideFilter();
                                    hideSearch();
                                    changeFragment(new AddCategoryFragment());
                                    DrawerLayout drawer_home = findViewById(R.id.drawer_layout);
                                    drawer_home.closeDrawer(GravityCompat.START);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    }));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setListeners() {
        try {
            tbIvHMenu.setOnClickListener(this);
            tbIVFilter.setOnClickListener(this);
            tbIVSearch.setOnClickListener(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tbIvHMenu:
                //open slid menu
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.tbIVFilter:
                break;

            case R.id.tbIVSearch:
                break;


            default:
                break;
        }
    }

    public void changeFragment(Fragment fragment) {
        try {
            String backStateName = fragment.getClass().getName();
            String fragmentTag = backStateName;

            FragmentManager manager = getSupportFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);


            if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.AppContainer, fragment, fragmentTag);
                fragmentTransaction.addToBackStack(backStateName);
                fragmentTransaction.commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            finish();
        } else {
            super.onBackPressed();
        }
    }


}
