package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecbabywear.CartAdapter;
import com.example.ecbabywear.Model.Piece;
import com.example.ecbabywear.PiecesViewModel;
import com.example.ecbabywear.R;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recview);

        recyclerView.setAdapter(new CartAdapter(this, (ArrayList<Piece>) PiecesViewModel.pieceList,1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}