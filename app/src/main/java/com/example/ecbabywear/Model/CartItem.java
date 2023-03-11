package com.example.ecbabywear.Model;

public class CartItem extends Piece{
    private int itemQuantity;

    public CartItem(String name, String URL, String price, String shortDescription, String longDescription, String status, int itemQuantity) {
        super(name, URL, price, shortDescription, longDescription, status);
        this.itemQuantity = itemQuantity;
    }

    public CartItem() {
    }

    public CartItem(Piece piece, int itemQuantity) {
        this.setName(piece.getName());
        this.setPrice(piece.getPrice());
        this.setItemQuantity(itemQuantity);
        this.setShortDescription(piece.getShortDescription());
        this.setLongDescription(piece.getLongDescription());
        this.setURL(piece.getURL());
        this.setStatus(piece.getStatus());
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
