package com.example.george.myfirstaplication;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by George on 4/22/2015.
 */
public class FoodActivity extends ActionBarActivity{
    ViewPager viewPager;
    FoodPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence titles[]={"Macros", "Meal plan"};
    int numberOfTabs = 2;
    String dataFromIntent;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        dataFromIntent = getIntent().getStringExtra("Nume");
       // Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        //setActionBar(toolbar);
        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        adapter = new FoodPagerAdapter(getSupportFragmentManager(), titles,numberOfTabs);
        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(viewPager);
        //  tabs.setViewPager();
    }


    }
