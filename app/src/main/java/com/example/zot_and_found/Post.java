package com.example.zot_and_found;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

public class Post {
    //String user; ---I don't know how to implement this yet
    String description;
    String question;
    String name;
    String imagePath;

    //Keep, needed for firebase
    public Post(){
    }

    public Post(String description, String question, String name, String imagePath)
    {
        this.description = description;
        this.question = question;
        this.name = name;
        this.imagePath = imagePath;
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

    public String getReference() {
        return imagePath;
    }
}
