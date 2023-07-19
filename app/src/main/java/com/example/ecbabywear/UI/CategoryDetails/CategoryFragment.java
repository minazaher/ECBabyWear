package com.example.ecbabywear.UI.CategoryDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.example.ecbabywear.Piece;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.example.ecbabywear.Utilis.StoreAdapter;
import com.example.ecbabywear.databinding.FragmentCategoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;




public class CategoryFragment extends Fragment {

    private static final String CATEGORY= "category";
    PiecesRepository piecesRepository;
    SearchView searchView ;
    FragmentCategoryBinding fragment;
    private String category;
    StoreAdapter adapter;
    Query query ;

    public CategoryFragment() {
        piecesRepository = new PiecesRepository();
    }

    public static CategoryFragment newInstance(String category) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
        searchView = getActivity().findViewById(R.id.products_search);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = FragmentCategoryBinding.inflate(getLayoutInflater(), container, false);
        piecesRepository.getShoesMutableLiveDataByCategory(category, arrivalsList -> {
            RecyclerView ShoesRecView = (RecyclerView) fragment.categoryProductsRecview;
            adapter = new StoreAdapter(getContext(), arrivalsList);
            ShoesRecView.setAdapter(adapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            ShoesRecView.setLayoutManager(gridLayoutManager);
        });

        return fragment.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Search();
    }

    public void Search(){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childRef = rootRef.child(category);
        query = childRef.orderByChild("name");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query.startAt(newText).endAt(newText + "\uf8ff").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Piece> items = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Piece item = snapshot.getValue(Piece.class);
                            items.add(item);
                        }
                        adapter.updatePiecesList(items);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Search error!" + databaseError);
                    }
                });
                return true;
            }
        });

    }
}
