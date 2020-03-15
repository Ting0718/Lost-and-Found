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

import com.example.zot_and_found.Post;
import com.example.zot_and_found.PostListAdapter;
import com.example.zot_and_found.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";
    private RecyclerView rvHome;
    private PostListAdapter adapter;
    private List<Post> postList;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        postList = new ArrayList<Post>();
        adapter = new PostListAdapter(getContext(), postList);

        rvHome = view.findViewById(R.id.rvHome);
        //setting adapter on recycler view
        rvHome.setAdapter(adapter);
        //setting layout manager on recycler view
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        getPosts();

    }

    private void getPosts() {
        firestore.collection("posts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            postList.add(document.toObject(Post.class));
                            Log.i(TAG, "added new post to list");

                            Log.i(TAG, "length of postlist is " + postList.size());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}