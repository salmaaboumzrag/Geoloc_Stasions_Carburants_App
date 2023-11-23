package com.example.android_project.modle;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoPoint implements Parcelable {
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    /**
     * No args constructor for use in serialization
     *
     */
    public GeoPoint() {
    }

    /**
     *
     * @param lon
     * @param lat
     */
    public GeoPoint(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }


    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
    }
    protected GeoPoint(Parcel in) {
        lon = (Double) in.readValue(Double.class.getClassLoader());
        lat = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<GeoPoint> CREATOR = new Creator<GeoPoint>() {
        @Override
        public GeoPoint createFromParcel(Parcel in) {
            return new GeoPoint(in);
        }

        @Override
        public GeoPoint[] newArray(int size) {
            return new GeoPoint[size];
        }
    };
}
