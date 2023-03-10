package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.currentPiece;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.R;

public class ItemDetails extends AppCompatActivity {
    TextView Name, Descripition, Price, Count;
    ImageView img;
    AppCompatButton Add, Drop, AddToCart;
    int C = 1;
    Double price;
    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Name = findViewById(R.id.tv_details_itemName);
        Descripition = findViewById(R.id.tv_details_itemDes);
        Price = findViewById(R.id.tv_details_itemPrice);
        img = findViewById(R.id.img_itemDetails);
        Count = findViewById(R.id.tv_details_itemCount);
        Add = findViewById(R.id.btn_plus_item);
        Drop = findViewById(R.id.btn_minus_item);
        AddToCart = findViewById(R.id.btn_add_to_cart);

        C = Integer.parseInt(Count.getText().toString());
        Name.setText(currentPiece.getName());
        Descripition.setText(currentPiece.getLongDescription());
        Price.setText(currentPiece.getPrice() + " L.E");
        Glide.with(this).asBitmap().load(currentPiece.getURL()).into(img);


        Add.setOnClickListener(view -> {
            C += 1;
            Count.setText(String.valueOf(C));
            price = Double.parseDouble(currentPiece.getPrice()) * C;
            Price.setText( price.toString()  +" L.E");
        });

        Drop.setOnClickListener(view -> {
            if (C != 0) {
                C-=1;
                Count.setText(String.valueOf(C));
                price = Double.parseDouble(currentPiece.getPrice()) * C;
                Price.setText( price.toString()  +" L.E");            }
        });

        AddToCart.setOnClickListener(view -> {
            CartItem cartItem = new CartItem(currentPiece, C);
            cart.add(cartItem);
            Toast.makeText(this, "Added Successfully!", Toast.LENGTH_SHORT).show();
        });
    }
}