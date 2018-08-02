package com.example.louisnelsonlevoride.popularmovies.TrailerData;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.louisnelsonlevoride.popularmovies.R;

import java.util.List;

public class TrailerRecycleViewAdapter extends RecyclerView.Adapter<TrailerViewHolder> {
    List<Trailer> trailers;
    Context context;

    public TrailerRecycleViewAdapter(Context context, List<Trailer> trailers) {
        this.trailers = trailers;
        this.context = context;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item,parent,false);
        return new TrailerViewHolder(view, this.context, this.trailers);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.title.setText(trailers.get(position).getTrailerName());
        holder.type.setText(trailers.get(position).getTrailerType());
        holder.site.setText(trailers.get(position).getTrailerSite());
    }
}
