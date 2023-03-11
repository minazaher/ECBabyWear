package com.example.ecbabywear.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String OrderID;
    private ArrayList<CartItem> Items;
    private Date OrderDate;
    private String TotalPrice;

    public Order(String orderID, ArrayList<CartItem> items, Date orderDate, String totalPrice) {
        OrderID = orderID;
        Items = items;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public Order() {
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public ArrayList<CartItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<CartItem> items) {
        Items = items;
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
