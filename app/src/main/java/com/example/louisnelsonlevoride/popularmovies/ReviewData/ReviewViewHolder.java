package com.example.louisnelsonlevoride.popularmovies.ReviewData;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.louisnelsonlevoride.popularmovies.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    TextView author;
    TextView reviewContent;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_id);
        reviewContent = itemView.findViewById(R.id.reviewContent);
    }
}
