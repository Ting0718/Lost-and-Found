package com.example.zot_and_found;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.example.zot_and_found.Adapters.RepliesAdapter;
import com.example.zot_and_found.Models.Post;
import com.example.zot_and_found.Models.Replier;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class AnswersActivity extends AppCompatActivity {

    public static final String TAG = "RepliesActivity";

    private Post post;
    String postName;

    List<Replier> repliers;
    private RecyclerView rvReplies;
    private RepliesAdapter repliesAdapter;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    //Intent intent = getIntent();
    //String postName = intent.getStringExtra("postName");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_activity);

        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        postName = post.getName();

        repliers = new ArrayList<>();

        rvReplies = findViewById(R.id.rvReplies);

        repliesAdapter = new RepliesAdapter(this, repliers);

        rvReplies.setAdapter(repliesAdapter);

        rvReplies.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvReplies.addItemDecoration(divider);

        getReplies();

    }

    private void getReplies() {
        firestore.collection(postName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                            repliers.add(document.toObject(Replier.class));
                        Log.i(TAG, "added new replier to list");

                        Log.i(TAG, "length of replier list is " + repliers.size());
                        repliesAdapter.notifyDataSetChanged();
                    }
                });
    }
}
