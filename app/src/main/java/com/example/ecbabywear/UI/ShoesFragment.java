package com.example.ecbabywear.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecbabywear.Model.Order;
import com.example.ecbabywear.Piece;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.PiecesRepository;
import com.example.ecbabywear.Utilis.OrdersAdapter;
import com.example.ecbabywear.Utilis.PiecesCallback;
import com.example.ecbabywear.Utilis.StoreAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShoesFragment extends Fragment {
    PiecesRepository piecesRepository;

    public ShoesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        piecesRepository = new PiecesRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_shoes_fragment, container, false);
        return view;
    }
}