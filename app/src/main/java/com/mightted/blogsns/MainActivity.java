package com.mightted.blogsns;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mightted.blogsns.Fragment.FindFragment;
import com.mightted.blogsns.Fragment.HomeFragment;
import com.mightted.blogsns.Fragment.MessageFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager mFragmentManager;
    private BottomNavigationView bottomNav;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;




    private Fragment homeFragment,findFragment,messageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initFragment();
        initListener();


    }


    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNav = (BottomNavigationView) findViewById(R.id.nav_bottom);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawer.addDrawerListener(drawerToggle);



        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }

    private void initFragment() {

        mFragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        setCurrentFragment(homeFragment);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void initListener() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_first:
                        if(homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        setCurrentFragment(homeFragment);
                        break;
                    case R.id.nav_second:
                        if(findFragment == null) {
                            findFragment = new FindFragment();
                        }
                        setCurrentFragment(findFragment);
                        break;
                    case R.id.nav_third:
                        if(messageFragment == null) {
                            messageFragment = new MessageFragment();
                        }
                        setCurrentFragment(messageFragment);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }

    private void setCurrentFragment(Fragment fragment) {
        if(fragment ==null) {
            return;
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.layout_content,fragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(Gravity.START);
        }
        return true;
    }
}
