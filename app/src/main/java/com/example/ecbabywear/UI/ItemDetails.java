package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.currentPiece;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.R;

public class ItemDetails extends AppCompatActivity {
    TextView Name, des, price;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Name = findViewById(R.id.tv_details_itemName);
        des = findViewById(R.id.tv_details_itemDes);
        price = findViewById(R.id.tv_details_itemPrice);
        img = findViewById(R.id.img_itemDetails);

        Name.setText(currentPiece.getName());
        des.setText(currentPiece.getLongDescription());
        price.setText(currentPiece.getPrice());
        Glide.with(this).asBitmap().load(currentPiece.getURL()).into(img);
    }
}