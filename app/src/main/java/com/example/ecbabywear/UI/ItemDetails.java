package com.example.ecbabywear.UI;

import static com.example.ecbabywear.ApplicationClass.cart;
import static com.example.ecbabywear.ApplicationClass.currentPiece;
import static com.example.ecbabywear.ApplicationClass.firebaseAuth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.example.ecbabywear.Model.CartItem;
import com.example.ecbabywear.R;
import com.example.ecbabywear.Repositories.WishlistRepository;

import java.util.HashMap;
import java.util.Map;

public class ItemDetails extends AppCompatActivity {
    TextView Name, Description, Price, Count;
    ImageView img, addToWishList;
    AppCompatButton Add, Drop, AddToCart;
    Button delete;
    int C = 1;
    Double price;
    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Name = findViewById(R.id.tv_details_itemName);
        Description = findViewById(R.id.tv_details_itemDes);
        Price = findViewById(R.id.tv_details_itemPrice);
        img = findViewById(R.id.img_itemDetails);
        Count = findViewById(R.id.tv_details_itemCount);
        Add = findViewById(R.id.btn_plus_item);
        Drop = findViewById(R.id.btn_minus_item);
        addToWishList = findViewById(R.id.btn_add_to_wishlist);
        AddToCart = findViewById(R.id.btn_add_to_cart);
        delete =  findViewById(R.id.delete_btn);

        wishlistCheck();

        C = Integer.parseInt(Count.getText().toString());
        Name.setText(currentPiece.getName());
        Description.setText(currentPiece.getLongDescription());
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

        delete.setOnClickListener(view -> {
            String User= firebaseAuth.getCurrentUser().getUid();
            WishlistRepository wishlistRepository = new WishlistRepository(User);
            if (wishlistRepository.isInWishlist(getCurrentPiece())){
                wishlistRepository.DeleteItemFromWishlist(getCurrentPiece());
            }
            else
                Toast.makeText(this, "Item is not in wishlist!", Toast.LENGTH_SHORT).show();
        });

        addToWishList.setOnClickListener(view -> {
            String User= firebaseAuth.getCurrentUser().getUid();
            WishlistRepository wishlistRepository = new WishlistRepository(User);
            if (!wishlistRepository.isInWishlist(getCurrentPiece())){
                addToWishList.setImageResource(R.drawable.ic_baseline_favorite_24);
                wishlistRepository.addItemToWishlist(getCurrentPiece());
                Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void wishlistCheck() {
        WishlistRepository wishlistRepository = new WishlistRepository(firebaseAuth.getCurrentUser().getUid());
        if (!wishlistRepository.isInWishlist(getCurrentPiece())) {
            addToWishList.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }
        else
            addToWishList.setImageResource(R.drawable.ic_baseline_favorite_24);
    }
    private Map<String, Object> getCurrentPiece(){
        Map<String, Object> piece = new HashMap<>();
        piece.put("name", currentPiece.getName());
        piece.put("URL",currentPiece.getURL());
        piece.put("price", currentPiece.getPrice());
        piece.put("shortDescription", currentPiece.getShortDescription());
        piece.put("longDescription", currentPiece.getLongDescription());
        piece.put("status", currentPiece.getStatus());
        return piece;
    }


}