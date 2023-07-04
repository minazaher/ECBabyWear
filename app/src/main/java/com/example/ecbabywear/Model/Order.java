package com.example.ecbabywear.Model;

import java.util.ArrayList;

public class Order {

    private String OrderID;
    private ArrayList<CartItem> Items;
    private String OrderDate;
    private String TotalPrice;
    private String Status;

    public Order(String orderID, ArrayList<CartItem> items, String orderDate, String totalPrice, String status) {
        OrderID = orderID;
        Items = items;
        OrderDate = orderDate.toString();
        TotalPrice = totalPrice;
        Status = status;
    }

    public Order() {
    }

    public int getSize(){
        return this.Items.size();
    }
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
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

    public String getStatus() {
        return Status;
    }
}
