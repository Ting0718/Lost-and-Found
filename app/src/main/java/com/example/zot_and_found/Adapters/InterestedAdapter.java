package com.example.zot_and_found.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zot_and_found.AnswersActivity;
import com.example.zot_and_found.DetailActivity;
import com.example.zot_and_found.Models.Post;
import com.example.zot_and_found.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.parceler.Parcels;

import java.util.List;

public class InterestedAdapter extends RecyclerView.Adapter<InterestedAdapter.ViewHolder> {

    Context context;
    List<Post> posts;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://zot-and-found.appspot.com");

    public InterestedAdapter(Context context, List<Post> posts) {
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

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPostName;
        private TextView tvDesc;
        private ImageView ivPicture;
        //private RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            //container = itemView.findViewById(R.id.container);
        }

        public void bind(final Post post) {
            StorageReference imageReference = storageRef.child(post.getReference());

            tvPostName.setText(post.getName());
            tvDesc.setText(post.getDescription());
            //GlideApp.with(context).load(imageReference).into(ivPicture);
            Glide.with(context).load(imageReference).into(ivPicture);
        }
    }
}
