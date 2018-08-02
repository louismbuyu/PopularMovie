package com.example.louisnelsonlevoride.popularmovies.ReviewData;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {
    String author;
    String reviewContent;

    public Review(String author, String reviewContent) {
        this.author = author;
        this.reviewContent = reviewContent;
    }

    protected Review(Parcel in) {
        author = in.readString();
        reviewContent = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(reviewContent);
    }
}
