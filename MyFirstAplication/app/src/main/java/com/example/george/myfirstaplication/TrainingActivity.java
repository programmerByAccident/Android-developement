package com.example.george.myfirstaplication;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TrainingActivity extends ActionBarActivity {

    ViewPager viewPager;
    PagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence titles[]={"Weights", "Cardio"};
    int numberOfTabs = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        adapter = new PageAdapter(getSupportFragmentManager(), titles, numberOfTabs);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
