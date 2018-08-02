package com.example.louisnelsonlevoride.popularmovies.TrailerData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.louisnelsonlevoride.popularmovies.R;

import java.util.List;

public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    TextView title;
    TextView type;
    TextView site;
    Context context;
    List<Trailer> trailers;

    public TrailerViewHolder(View itemView, Context context, List<Trailer> trailers) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        title = itemView.findViewById(R.id.trailer_title_id);
        type = itemView.findViewById(R.id.trailer_type_id);
        site = itemView.findViewById(R.id.trailer_site_id);
        this.context = context;
        this.trailers = trailers;
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        Trailer item = trailers.get(position);
        Uri webPage = Uri.parse("https://www.youtube.com/watch?v="+item.getKey());
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
    }


    @Override
    public boolean onLongClick(View view) {
        int position = getAdapterPosition();
        Trailer item = trailers.get(position);
        String urlToShare = "https://www.youtube.com/watch?v="+item.getKey();
        String mimeType = "text/plain";
        String title = trailers.get(position).getTrailerName();
        Activity activity = (Activity) context;
        Intent intent =  ShareCompat.IntentBuilder.from(activity)
                .setChooserTitle(title)
                .setType(mimeType)
                .setText(urlToShare)
                .getIntent();
        if (intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
        return true;
    }
}
