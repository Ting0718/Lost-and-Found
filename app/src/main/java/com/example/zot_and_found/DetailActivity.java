package com.example.zot_and_found;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zot_and_found.Models.Post;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = "DeatailActivity";

    private Post post;

    private TextView tvName;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);

        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvName.setText(post.getName());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://zot-and-found.appspot.com");
        StorageReference imageReference = storageRef.child(post.getReference());

        Glide.with(this).load(imageReference).into(ivImage);

        tvDescription.setText(post.getDescription());
        tvQuestion.setText(post.getQuestion());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"guess you pushed the button, good for you");
            }
        });
    }
}
