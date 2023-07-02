package com.example.ecbabywear.Model;

import com.example.ecbabywear.Piece;

import java.util.List;

public class Wishlist {
    private String wishlistId;
    private List<Piece> items;

    public Wishlist() {}


    public Wishlist(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Wishlist(String wishlistId, List<Piece> items) {
        this.wishlistId = wishlistId;
        this.items = items;
    }

    public List<Piece> getItems() {
        return items;
    }

    public void setItems(List<Piece> items) {
        this.items = items;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

}
