package com.example.victory.sincerely.Tabs;

import com.example.victory.sincerely.R;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import android.view.MenuItem;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;
import android.content.Context;


public class Tabs extends AppCompatActivity implements BaseFragment.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener {


    private TabLayout tablayout;

    private String tabNames[] = {"first", "second", "third"};


    private int[] tabIconsUnSelected = {
            R.drawable.ic_home_black_24dp,
            R.drawable.play_black,
            R.drawable.gallery_black};

    private int[] tabIconsSelected = {
            R.drawable.ic_home_white_24dp,
            R.drawable.play_color,
            R.drawable.gallery_color};


    private FragNavController mNavController;

    private FragmentHistory fragmentHistory;

    public static Drawable setDrawableSelector(Context context, int normal, int selected) {

        Drawable state_normal = ContextCompat.getDrawable(context, normal);

        Drawable state_pressed = ContextCompat.getDrawable(context, selected);

        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_selected},
                state_pressed);
        drawable.addState(new int[]{android.R.attr.state_enabled},
                state_normal);

        return drawable;
    }

    public static ColorStateList setTextselector(int normal, int pressed) {
        ColorStateList colorStates = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_selected},
                        new int[]{}
                },
                new int[]{
                        pressed,
                        normal});
        return colorStates;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        initView();

        initTab();

        setupFragmentHistory(savedInstanceState);

        switchTab(0);

        setupTabLayout();


    }

    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }

    private void initTab() {
        if (tablayout != null) {
            for (int i = 0; i < tabNames.length; i++) {
                tablayout.addTab(tablayout.newTab());
                TabLayout.Tab tab = tablayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(getTabView(i));
            }
        }

    }

    private void setupTabLayout() {
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentHistory.push(tab.getPosition());
                switchTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                mNavController.clearStack();
                switchTab(tab.getPosition());

            }
        });
    }

    private void setupFragmentHistory(Bundle savedInstanceState) {
        fragmentHistory = new FragmentHistory();


        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, tabNames.length)
                .build();
    }

    private void switchTab(int position) {
        mNavController.switchTab(position);
    }

    @Override
    public void onBackPressed() {

        if (!mNavController.isRootFragment()) {
            mNavController.popFragment();
        } else {

            if (fragmentHistory.isEmpty()) {
                super.onBackPressed();
            } else {
                if (fragmentHistory.getStackSize() > 1) {

                    int position = fragmentHistory.popPrevious();

                    switchTab(position);

                    updateTabSelection(position);

                } else {

                    switchTab(0);

                    updateTabSelection(0);

                    fragmentHistory.emptyStack();
                }
            }

        }
    }

    private void updateTabSelection(int currentTab) {

        for (int i = 0; i < tabNames.length; i++) {
            TabLayout.Tab selectedTab = tablayout.getTabAt(i);
            if (currentTab != i) {
                selectedTab.getCustomView().setSelected(false);
            } else {
                selectedTab.getCustomView().setSelected(true);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(Tabs.this).inflate(R.layout.view_tabs, null);

        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        icon.setImageDrawable(setDrawableSelector(Tabs.this, tabIconsUnSelected[position], tabIconsSelected[position]));


        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {

            case FragNavController.TAB1:
                return FirstFragment.newInstance();
            case FragNavController.TAB2:
                return SecondFragment.newInstance();
            case FragNavController.TAB3:
                return ThirdFragment.newInstance();


        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
    }

}
