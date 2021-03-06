package com.npu.mis.pcrs;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.npu.mis.pcrs.BottomNavigation.BaseFragment;
import com.npu.mis.pcrs.BottomNavigation.BottomNavigationViewHelper;
import com.npu.mis.pcrs.BottomNavigation.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    //bottomNavigation
    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    //navigationDrawer
    private DrawerLayout drawerLayout;
    private NestedScrollView nestedScrollView;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int CurrentMenuItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_navigation_main);

        initView();
        initData();
        setToolbar();
        setNavigationDrawer();
        setBottomNavigation();
        setViewPager(viewPager);

    }

    private void initView() {
        //bottomNavigation
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigation_view);
        //navigationDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nestedScrollView = (NestedScrollView) drawerLayout.findViewById(R.id.nestedscroll_view);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initData() {

    }

    private void setBottomNavigation() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                viewPager.setCurrentItem(0);
                                toolbar.setTitle(R.string.nav_home);
                                break;
                            case R.id.nav_search:
                                viewPager.setCurrentItem(1);
                                toolbar.setTitle(R.string.nav_home);
                                break;
                            case R.id.nav_favorites:
                                viewPager.setCurrentItem(2);
                                toolbar.setTitle(R.string.nav_home);
                                break;
                            case R.id.nav_settings:
                                viewPager.setCurrentItem(3);
                                toolbar.setTitle(R.string.nav_home);
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    private void setNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (!(item == navigationView.getMenu().getItem(CurrentMenuItem))) {
                    switch (item.getItemId()) {
                        case R.id.nav_myself:
                            Snackbar.make(navigationView, "你點擊了" + getString(R.string.myself), Snackbar.LENGTH_LONG).setAction("確定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                            //finish();
                            break;
                        case R.id.nav_about:
                            Snackbar.make(navigationView, "你點擊了" + getString(R.string.about), Snackbar.LENGTH_LONG).setAction("確定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                            //finish();
                            break;
                        case R.id.nav_logout:
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(getString(R.string.logout))
                                    .setMessage("你確定要登出嗎?")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //finish();
                                        }
                                    })
                                    .setNegativeButton("否", null).create();

                            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface dialog) {
                                    assert alertDialog.getWindow() != null;
                                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.color_Snow);
                                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                                    //.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.notif_tb_title_ts));
                                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                                }
                            });
                            alertDialog.show();
                            break;
                    }
                } else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setViewPager(ViewPager viewPager) {
        this.viewPager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (menuItem != null) {
                            menuItem.setChecked(false);
                        } else {
                            bottomNavigationView.getMenu().getItem(0).setChecked(false);
                        }
                        menuItem = bottomNavigationView.getMenu().getItem(position);
                        menuItem.setChecked(true);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BaseFragment.newInstance("首頁"), "");
        adapter.addFragment(BaseFragment.newInstance("探索"), "");
        adapter.addFragment(BaseFragment.newInstance("收藏"), "");
        adapter.addFragment(BaseFragment.newInstance("設定"), "");
        viewPager.setAdapter(adapter);
    }

}
