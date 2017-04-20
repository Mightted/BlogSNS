package com.mightted.blogsns;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import com.mightted.blogsns.Fragment.FindFragment;
import com.mightted.blogsns.Fragment.HomeFragment;
import com.mightted.blogsns.Fragment.MessageFragment;
import com.mightted.blogsns.Utils.DrawerUtil;
import com.mightted.blogsns.Utils.LogUtil;
import com.mightted.blogsns.Utils.UserManager;
import com.mikepenz.materialdrawer.AccountHeader;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FragmentManager mFragmentManager;
    private BottomNavigationView bottomNav;

    private AccountHeader mAccountHeader;
    private Drawer mDrawer;
    private IProfile myProfile;




    private Fragment homeFragment,findFragment,messageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        LogUtil.i("MainActivity","onCreate初始化");
        initFragment();
        initListener();
        initHeader();
        initDrawer();



    }



    /**
     * 初始化AccountHeader
     */
    private void initHeader() {

        //初始化用户信息，待封装
        UserManager manager = UserManager.getInstance();
        LogUtil.i("MainActivity","初始化");
        mAccountHeader = manager.getDefaultUser(this);
//        myProfile = new ProfileDrawerItem().withName("user").withIcon(Uri.parse("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460")).withIdentifier(DrawerUtil.Account_Check);
//        mAccountHeader = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.img_nav_head_bg)
//                .withTranslucentStatusBar(false)
//                .addProfiles(
//                        myProfile,
//                        new ProfileSettingDrawerItem()
//                                .withName(this.getString(R.string.change_account))
//                                .withDescription(this.getString(R.string.change_account_des))
//                                .withIcon(R.drawable.ic_account_add_24dp)
//                                .withIdentifier(DrawerUtil.Account_Add)
//                )
//                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
//                    @Override
//                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
//                        switch ((int)profile.getIdentifier()) {
//                            case DrawerUtil.Account_Add:
//                                //添加操作
//                                break;
//                            case DrawerUtil.Account_Check:
//                                //登录操作
//                                break;
//                            default:
//                                break;
//                        }
//                        return true;
//                    }
//                })
//                .build();
    }


    private void initDrawer() {
        mDrawer = new DrawerBuilder(this)
                .withRootView(R.id.content_drawer)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(mAccountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_user)).withIcon(R.drawable.ic_nav_user_24dp).withIdentifier(DrawerUtil.Drawer_User).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_follow)).withIcon(R.drawable.ic_nav_follow_24dp).withIdentifier(DrawerUtil.Drawer_Follow).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_blog)).withIcon(R.drawable.ic_nav_blog_24dp).withIdentifier(DrawerUtil.Drawer_Blog).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_collection)).withIcon(R.drawable.ic_nav_collection_24dp).withIdentifier(DrawerUtil.Drawer_Collection).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_history)).withIcon(R.drawable.ic_nav_history_24dp).withIdentifier(DrawerUtil.Drawer_History).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_settings)).withIcon(R.drawable.ic_nav_settings_24dp).withIdentifier(DrawerUtil.Drawer_Settings).withSelectable(false),
                        new PrimaryDrawerItem().withName(this.getString(R.string.nav_suggestion)).withIcon(R.drawable.ic_nav_suggestion_24dp).withIdentifier(DrawerUtil.Drawer_suggestion).withSelectable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(!UserManager.getInstance().getLoginStatus()) {
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        } else {
                            switch ((int)drawerItem.getIdentifier()) {
                                case DrawerUtil.Drawer_User:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_Follow:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_Blog:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_Collection:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_History:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_Settings:
                                    //添加操作
                                    break;
                                case DrawerUtil.Drawer_suggestion:
                                    //添加操作
                                    break;
                                default:
                                    break;
                            }
                        }

                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(true)
                .build();
    }


    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNav = (BottomNavigationView) findViewById(R.id.nav_bottom);
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

//        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close){
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                invalidateOptionsMenu();
//            }
//        };



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

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }

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
    protected void onStop() {
        super.onStop();
        LogUtil.i("MainActivity","stop被调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("MainActivity","Destroy被调用");
    }

    @Override
    public void onBackPressed() {
        if(mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DrawerUtil.Account_Login:
                if(resultCode == RESULT_OK) {
                    initHeader();
                    mDrawer.setHeader(mAccountHeader.getView());
                    mAccountHeader.setDrawer(mDrawer);
                }
        }
    }
}
