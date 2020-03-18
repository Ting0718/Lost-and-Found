package com.example.zot_and_found.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zot_and_found.Adapters.MyPostAdapter;
import com.example.zot_and_found.Models.Post;
import com.example.zot_and_found.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyPostsFragment extends Fragment {
    public static final String TAG = "MyPostFragment";
    List<Post> posts;
    private RecyclerView rvPosts;
    private  MyPostAdapter myPostAdapter;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    String emailName = mFirebaseUser.getEmail();

    public MyPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_my_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        posts = new ArrayList<>();

        rvPosts = view.findViewById(R.id.rvMyPosts);

        myPostAdapter = new MyPostAdapter(getContext(), posts);

        rvPosts.setAdapter(myPostAdapter);

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        getMyPost();

    }



    private void getMyPost() {
        firestore.collection(emailName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                            posts.add(document.toObject(Post.class));
                            Log.i(TAG, "added new post to list");

                            Log.i(TAG, "length of postlist is " + posts.size());
                            myPostAdapter.notifyDataSetChanged();
                        }
                });
    }
}
