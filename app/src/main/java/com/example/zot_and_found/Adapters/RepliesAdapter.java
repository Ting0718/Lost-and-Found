package com.example.zot_and_found.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zot_and_found.AnswersActivity;
import com.example.zot_and_found.Models.Replier;
import com.example.zot_and_found.R;

import java.util.List;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.ViewHolder> {

    Context context;
    List<Replier> repliers;

    public RepliesAdapter(Context context, List<Replier> repliers) {
        this.context = context;
        this.repliers = repliers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View replyView = LayoutInflater.from(context).inflate(R.layout.message_preview, parent, false);
        return new ViewHolder(replyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Replier replier = repliers.get(position);
        holder.bind(replier);

    }

    @Override
    public int getItemCount() {
        return repliers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etName;
        TextView etDescription;
        ConstraintLayout clContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.etName);
            etDescription = itemView.findViewById(R.id.etDescription);
            clContainer = itemView.findViewById(R.id.clContainer);
        }

        public void bind(final Replier replier) {
            etName.setText(replier.getUserEmail());
            etDescription.setText("The answer or response is: \n" + replier.getReply());
        }
    }
}
