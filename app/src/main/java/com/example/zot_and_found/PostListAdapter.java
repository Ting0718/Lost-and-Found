package com.example.zot_and_found;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostListAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //TODO: change fragment_compose to post_list

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_compose,parent,false);
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

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUser;
        private TextView tvDesc;
        private TextView tvQuestion;
        private ImageView ivPicture;
        public ViewHolder(@NonNull View view){
            super(view);
            tvUser = view.findViewById(R.id.tvUser);
            tvDesc = view.findViewById(R.id.tvDesc);
            tvQuestion = view.findViewById(R.id.tvQuestion);
            ivPicture = view.findViewById(R.id.ivPicture);

        }

        public void bind(Post post) {
            //TODO: retrieve post data from firebase

        }
    }
}
