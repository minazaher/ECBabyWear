package com.example.ecbabywear.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String UserID;
    private String OrderID;
    private ArrayList<CartItem> Items;
    private LocalDate OrderDate;
    private String TotalPrice;

    public Order() {
    }

    public Order(String userID, String orderID, ArrayList<CartItem> items, LocalDate orderDate, String totalPrice) {
        UserID = userID;
        OrderID = orderID;
        Items = items;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public Order(String orderID, ArrayList<CartItem> items, LocalDate orderDate, String totalPrice) {
        OrderID = orderID;
        Items = items;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public ArrayList<CartItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<CartItem> items) {
        Items = items;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        OrderDate = orderDate;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderID='" + OrderID + '\'' +
                ", OrderPieces=" + Items +
                ", OrderDate=" + OrderDate +
                ", TotalPrice='" + TotalPrice + '\'' +
                '}';
    }
}
