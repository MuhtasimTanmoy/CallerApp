package com.example.tanmoy.callerapp.activity;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tanmoy.callerapp.R;
import com.example.tanmoy.callerapp.adapters.HomePageAdapter;


public class HomepageActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new HomePageAdapter(getSupportFragmentManager(),getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);



    }



}
