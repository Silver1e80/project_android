package com.npu.mis.pcrs;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }

    private void initData(){

    }

    private void  initListener(){
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.nav_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.nav_search:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.nav_favorites:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.nav_settings:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                }
        );

        viewPager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if(menuItem != null){
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

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("首頁"));
        adapter.addFragment(BaseFragment.newInstance("探索"));
        adapter.addFragment(BaseFragment.newInstance("收藏"));
        adapter.addFragment(BaseFragment.newInstance("設定"));
        viewPager.setAdapter(adapter);
    }
}
