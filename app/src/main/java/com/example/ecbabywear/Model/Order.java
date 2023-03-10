package com.example.ecbabywear.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String OrderID;
    private ArrayList<CartItem> Items;
    private LocalDate OrderDate;
    private String TotalPrice;

    public Order() {
    }

    public Order(String orderID, ArrayList<CartItem> items, LocalDate orderDate, String totalPrice) {
        OrderID = orderID;
        Items = items;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
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
