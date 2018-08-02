package com.example.louisnelsonlevoride.popularmovies.TrailerData;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable {
    String key;
    String trailerName;
    String trailerType;
    String trailerSite;

    public Trailer(String key, String trailerName, String trailerType, String trailerSite) {
        this.key = key;
        this.trailerName = trailerName;
        this.trailerType = trailerType;
        this.trailerSite = trailerSite;
    }

    protected Trailer(Parcel in) {
        key = in.readString();
        trailerName = in.readString();
        trailerType = in.readString();
        trailerSite = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getTrailerSite() {
        return trailerSite;
    }

    public void setTrailerSite(String trailerSite) {
        this.trailerSite = trailerSite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(trailerName);
        parcel.writeString(trailerType);
        parcel.writeString(trailerSite);
    }
}
