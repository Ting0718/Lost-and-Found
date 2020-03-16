package com.example.zot_and_found.Models;

import org.parceler.Parcel;

@Parcel
public class Post {
    public static final String TAG = "Post";
    //String user; ---I don't know how to implement this yet
    String description;
    String question;
    String name;
    String reference;

    //Keep, needed for firebase
    public Post(){

    }

    public Post(String description, String question, String name, String imagePath)
    {
        this.description = description;
        this.question = question;
        this.name = name;
        this.reference = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getQuestion() {
        return question;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

}
