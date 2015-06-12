package com.example.george.myfirstaplication;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    CharSequence tabs[];
    int numberOfTabs;

    public PageAdapter(FragmentManager mn, CharSequence mytabs[], int myNumberOfTabs){
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
            FragmentTab1 tab1 = new FragmentTab1();
            return tab1;
        }
        else
            if(position==1){
                FragmentTab2 tab2 = new FragmentTab2();
                return tab2;

            }
/*                if(position==2){
                    FragmentTab3 tab3 = new FragmentTab3();
                    return tab3;
                }*/
        else
                    return null;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
