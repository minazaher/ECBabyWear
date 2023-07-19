package com.example.ecbabywear.UI.CategoryDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.example.ecbabywear.UI.CategoryDetails.CategoryFragment;
import com.example.ecbabywear.Utilis.PagerAdapter;
import com.example.ecbabywear.databinding.ActivityCategoryProductsBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class CategoryProducts extends AppCompatActivity {
    ActivityCategoryProductsBinding binding;
    PiecesRepository piecesRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.include.textView11.setText("Browse Products");
        binding.productsSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        piecesRepository =  new PiecesRepository();


        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        categories.add("New Arrivals");
        categories.add("shoes");
        categories.add("Shorts");
        categories.add("pajamas");
        categories.add("sets");

        for (int i = 0; i < 5; i++) {
            fragments.add(CategoryFragment.newInstance(categories.get(i)));
        }

        int tabIndex = getIntent().getIntExtra("tabIndex", 0);

        PagerAdapter pagerAdapter = new PagerAdapter(this, fragments);
        binding.categoryViewPager.setAdapter(pagerAdapter);
        binding.categoryViewPager.setCurrentItem(tabIndex);
        new TabLayoutMediator(binding.categoryTabLayout, binding.categoryViewPager, (tab, position) ->
                tab.setText(categories.get(position))).attach();
    }
//
//    public void onSearch(String query) {
//        ViewPager2 viewPager = binding.categoryViewPager;
//        PagerAdapter adapter = (PagerAdapter) viewPager.getAdapter();
//        CategoryFragment myFragment = (CategoryFragment) adapter.getCurrentFragment(viewPager.getCurrentItem());
//        myFragment.filterData(query);
//    }


    public void searchList(String name){
        ArrayList<Piece> searchList = new ArrayList<>();

        for (Piece p :searchList) {
            if(p.getName().toLowerCase().contains(name.toLowerCase())){
                searchList.add(p);
            }
        }
    }
}