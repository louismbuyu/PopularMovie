package com.example.louisnelsonlevoride.popularmovies.ReviewData;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louisnelsonlevoride.popularmovies.R;

import java.util.List;

public class ReviewRecycleViewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    List<Review> reviews;
    Context context;

    public ReviewRecycleViewAdapter(Context context, List<Review> reviews) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.author.setText(reviews.get(position).getAuthor());
        holder.reviewContent.setText(reviews.get(position).getReviewContent());
    }
}
