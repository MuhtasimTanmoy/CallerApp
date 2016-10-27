package com.example.tanmoy.callerapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tanmoy.callerapp.activity.Fragment1;
import com.example.tanmoy.callerapp.activity.Fragment2;


/** * Created by Tanmoy on 10/14/2016.
 */
public class HomePageAdapter extends FragmentPagerAdapter {

    private String fragments [] = {"Send SMS","Make Call"};

    public HomePageAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
