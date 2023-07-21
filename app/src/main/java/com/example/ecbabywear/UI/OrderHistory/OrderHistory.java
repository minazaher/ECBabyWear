package com.example.ecbabywear.UI.OrderHistory;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Utilis.PagerAdapter;
import com.example.ecbabywear.databinding.ActivityOrderHistoryBinding;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;


public class OrderHistory extends AppCompatActivity {
    ActivityOrderHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ordersToolbar.textView11.setText("Order History");


        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> orderTypes = new ArrayList<>();
        orderTypes.add("Completed");
        orderTypes.add("Cancelled");

        for (int i = 0; i < 2; i++) {
            fragments.add(OrderHistoryFragment.newInstance(orderTypes.get(i)));
        }


        PagerAdapter pagerAdapter = new PagerAdapter(this, fragments);
        binding.viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
                tab.setText(orderTypes.get(position))).attach();
    }
}