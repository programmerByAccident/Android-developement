package com.example.george.myfirstaplication;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FoodPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence tabs[];
    int numberOfTabs;

    public FoodPagerAdapter(FragmentManager mn, CharSequence mytabs[], int myNumberOfTabs){
        super(mn);
        this.numberOfTabs = myNumberOfTabs;
        this.tabs = mytabs;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            FoodTab1 tab1 = new FoodTab1();
            return tab1;
        }
        else
        if(position==1){
            FoodTab2 tab2 = new FoodTab2();
            return tab2;

        }
        else
            return null;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
