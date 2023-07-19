package com.example.ecbabywear.UI.OrderHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.OrderRepository;
import com.google.android.material.tabs.TabLayout;


public class OrderHistory extends AppCompatActivity {
    private TabLayout tabLayout;
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    OrderRepository orderRepository;
    OrderHistoryFragment completedOrdersFragment;
    CanceledOrderHistoryFragment canceledOrderHistoryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        orderRepository=  new OrderRepository();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        completedOrdersFragment = new OrderHistoryFragment();
        canceledOrderHistoryFragment = new CanceledOrderHistoryFragment();

        fragmentAdapter.addFragment(completedOrdersFragment, "Completed");
        fragmentAdapter.addFragment(canceledOrderHistoryFragment, "Cancelled");

        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}