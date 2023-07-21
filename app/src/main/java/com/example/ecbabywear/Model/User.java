package com.example.ecbabywear.Model;

import android.location.Address;

import java.util.ArrayList;

public class User {
    private String Name;
    private String Email;
    private String Password;
    private String profilePicture;
    private String Address;
    private ArrayList<Order> OrdersHistory;
    private ArrayList<Request> Requests;
    private Wishlist wishlist;

    public User() {
    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
        this.wishlist = new Wishlist();
    }

    public User(String name, String email, String password, String profilePicture, String address) {
        Name = name;
        Email = email;
        Password = password;
        this.profilePicture = profilePicture;
        this.wishlist = new Wishlist();
        this.Address = address;

    }

    public User(String name, String email, String password, ArrayList<Order> ordersHistory, ArrayList<Request> requests) {
        Name = name;
        Email = email;
        Password = password;
        OrdersHistory = ordersHistory;
        Requests = requests;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
