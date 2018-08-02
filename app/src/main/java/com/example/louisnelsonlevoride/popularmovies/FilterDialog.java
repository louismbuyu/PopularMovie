package com.example.louisnelsonlevoride.popularmovies;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class FilterDialog {
    private final static String PARAM_POPULAR_SORT = "popularity.desc";
    private final static String PARAM_VOTE_AVERAGE_SORT = "vote_average.desc";
    public Button applyFilter;
    public RadioButton radioButtonPopular;
    public RadioButton radioButtonRating;
    public RadioButton radioButtonFavourites;
    public Dialog dialog;
    public void showDialog(Activity activity, String msg){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.filter_dialog);
        TextView text = (TextView) dialog.findViewById(R.id.filter_title_id);
        radioButtonPopular = dialog.findViewById(R.id.radio_popular);
        radioButtonRating = dialog.findViewById(R.id.radio_rating);
        radioButtonFavourites = dialog.findViewById(R.id.radio_favourite);
        applyFilter = dialog.findViewById(R.id.applyFilterBtn_id);
        dialog.show();
    }
}
