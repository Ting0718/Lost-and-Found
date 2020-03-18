package com.example.zot_and_found.Models;


import java.util.ArrayList;

public class User {
    private String userEmail;
    private ArrayList<Post> posts;


    public User(String userEmail, ArrayList<Post> posts) {
        this.userEmail= userEmail;
        this.posts = posts;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
