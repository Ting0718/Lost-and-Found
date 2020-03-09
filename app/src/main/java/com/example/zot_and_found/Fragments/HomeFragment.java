package com.example.zot_and_found.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zot_and_found.Post;
import com.example.zot_and_found.PostListAdapter;
import com.example.zot_and_found.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvHome;
    private PostListAdapter adapter;
    private List<Post> list_of_posts;
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
        list_of_posts = new ArrayList<>();
        adapter = new PostListAdapter(getContext(), list_of_posts);

        rvHome = view.findViewById(R.id.rvHome);
        //setting adapter on recycler view
        rvHome.setAdapter(adapter);
        //setting layout manager on recycler view
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        getPosts();

    }

    private void getPosts() {
        //TODO: Obtain posts from firebase
    }

}