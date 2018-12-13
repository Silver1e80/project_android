package com.npu.mis.pcrs;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NestedScrollView nestedScrollView;
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected int CurrentMenuItem = 0;

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_navigation_main, null);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigation_drawer);
        nestedScrollView = (NestedScrollView) drawerLayout.findViewById(R.id.nestedscroll_view);
        getLayoutInflater().inflate(layoutResId, nestedScrollView, true);
        super.setContentView(drawerLayout);
        initView();
        setNavigationDrawer();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                            final AlertDialog alertDialog = new AlertDialog.Builder(BaseActivity.this)
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

    public void setToolbar() {
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
}
