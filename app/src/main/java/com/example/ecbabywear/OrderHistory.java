package com.example.ecbabywear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderHistory extends AppCompatActivity {
    private TabLayout tabLayout;
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new OrderHistoryFragment(), "Completed");
        fragmentAdapter.addFragment(new OrderHistoryFragment(), "Canceled");
        viewPager.setAdapter(fragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);


    }
}