package com.example.android.quakereport;

/**
 * Created by Mengqi on 1/22/17.
 */

public class Earthquake {
    private double magnitude;
    private String location;
    private long date;
    private String url;

    public Earthquake(double magnitude, String location, long date, String url) {
        this.date = date;
        this.magnitude = magnitude;
        this.location = location;
        this.url = url;

    }

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
