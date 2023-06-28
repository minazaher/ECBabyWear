package com.example.ecbabywear.Model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Piece {
    private String Name;
    private String URL;
    private String Price;
    private String ShortDescription;
    private String LongDescription;
    private String Status;


    public Piece() {
    }

    public Piece(String name, String URL, String price) {
        Name = name;
        this.URL = URL;
        Price = price;
    }

    public Piece(String name, String URL, String price, String shortDescription, String longDescription) {
        Name = name;
        this.URL = URL;
        Price = price;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
    }

    public Piece(String name, String URL, String price, String shortDescription, String longDescription, String status) {
        Name = name;
        this.URL = URL;
        Price = price;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    @Override
    public String toString() {
        return "Piece{" +
                "Name='" + Name + '\'' +
                ", URL='" + URL + '\'' +
                ", Price='" + Price + '\'' +
                ", ShortDescription='" + ShortDescription + '\'' +
                ", LongDescription='" + LongDescription + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String URL){
        Glide.with(imageView).asBitmap().load(URL).into(imageView);
    }
}

