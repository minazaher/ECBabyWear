package com.example.ecbabywear.Model;

import com.google.type.DateTime;

import java.util.ArrayList;

public class Order {
    private String OrderID;
    private ArrayList<Piece> OrderPieces;
    private DateTime OrderDate;
    private String TotalPrice;

    public Order() {
    }

    public Order(String orderID, ArrayList<Piece> orderPieces, DateTime orderDate, String totalPrice) {
        OrderID = orderID;
        OrderPieces = orderPieces;
        OrderDate = orderDate;
        TotalPrice = totalPrice;
    }

    public ArrayList<Piece> getOrderPieces() {
        return OrderPieces;
    }

    public void setOrderPieces(ArrayList<Piece> orderPieces) {
        OrderPieces = orderPieces;
    }

    public DateTime getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(DateTime orderDate) {
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
                ", OrderPieces=" + OrderPieces +
                ", OrderDate=" + OrderDate +
                ", TotalPrice='" + TotalPrice + '\'' +
                '}';
    }
}
