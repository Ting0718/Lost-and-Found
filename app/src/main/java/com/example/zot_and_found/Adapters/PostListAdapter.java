package com.example.zot_and_found.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zot_and_found.DetailActivity;
import com.example.zot_and_found.Models.Post;
import com.example.zot_and_found.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import org.parceler.Parcels;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    public static final String TAG = "PostListAdapter";

    private Context context;
    private List<Post> posts;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://zot-and-found.appspot.com");


    public PostListAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
        Log.i(TAG, "currently binding post at position " + position);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPostName;
        private TextView tvDesc;
        private TextView tvQuestion;
        private ImageView ivPicture;
        private RelativeLayout container;
        public ViewHolder(@NonNull View view){
            super(view);
            tvPostName = view.findViewById(R.id.tvName);
            tvDesc = view.findViewById(R.id.tvDesc);
            ivPicture = view.findViewById(R.id.ivPicture);
            container = view.findViewById(R.id.container);
        }

        public void bind(final Post post) {
            StorageReference imageReference = storageRef.child(post.getReference());

            tvPostName.setText(post.getName());
            tvDesc.setText(post.getDescription());
            //GlideApp.with(context).load(imageReference).into(ivPicture);
            Glide.with(context).load(imageReference).into(ivPicture);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });
        }
    }

}

