package com.example.ecbabywear.Model;

import com.example.ecbabywear.Piece;

import java.util.ArrayList;

public class User {
    private String Name;
    private String Email;
    private String Password;
    private ArrayList<Order> OrdersHistory;
    private ArrayList<Request> Requests;
    private Piece[] wishlist;

    public User() {
    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
        this.wishlist = new Piece[15];
    }

    public User(String name, String email, String password, ArrayList<Order> ordersHistory, ArrayList<Request> requests) {
        Name = name;
        Email = email;
        Password = password;
        OrdersHistory = ordersHistory;
        Requests = requests;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public ArrayList<Order> getOrdersHistory() {
        return OrdersHistory;
    }

    public void setOrdersHistory(ArrayList<Order> ordersHistory) {
        OrdersHistory = ordersHistory;
    }

    public ArrayList<Request> getRequests() {
        return Requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        Requests = requests;
    }
}
