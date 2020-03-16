package com.example.zot_and_found.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONException;
import org.json.JSONObject;

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

    public Bitmap getBitMap()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://zot-and-found.appspot.com");
        StorageReference imageReference = storageRef.child(getReference());
        final Bitmap[] imageBM = new Bitmap[1];
        final long TEN_MEGABYTE = 4096 * 4096;
        imageReference.getBytes(TEN_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                imageBM[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.e(TAG, "un-oh, didn't get the image");
                return;
            }
        });
        Log.i(TAG, "the string of imageBM is " + imageBM[0].toString());
        return imageBM[0];
    }


}
