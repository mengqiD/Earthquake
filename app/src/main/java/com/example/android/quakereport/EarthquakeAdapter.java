package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Mengqi on 1/22/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    //control how the list displays.
    public View getView(int position, View contextView, ViewGroup parent) {
        //check if there is an existing view
        View listView = contextView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the earthquake info associated with
        Earthquake currentQuake = getItem(position);


        //get magnitude info
        double magnitude = currentQuake.getMagnitude();
        TextView magView = (TextView) listView.findViewById(R.id.mag);
        magView.setText(formatMag(magnitude));

        //get primalocation info
        TextView locView = (TextView) listView.findViewById(R.id.primaryLocation);
        //get primalocation info
        TextView locView2 = (TextView) listView.findViewById(R.id.secondLocation);

        //separate location info
        String location = currentQuake.getLocation();
        if (location.contains("of")) {
            String[] locations = location.split("of ");
            String secLocation = locations[0];
            String priLocation = locations[1];
            //set location info
            locView.setText(priLocation);
            locView2.setText(secLocation + "of");
        } else {
            locView.setText(currentQuake.getLocation());
            locView2.setText(R.string.near);
        }

        //set background
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitude);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //get date info
        Date dateObject = new Date(currentQuake.getDate());
        String dateFormat = formatDate(dateObject);
        TextView dateView = (TextView) listView.findViewById(R.id.date);
        dateView.setText(dateFormat);

        //get time info
        TextView timeView = (TextView) listView.findViewById(R.id.time);
        String timeFormat = formatTime(dateObject);
        timeView.setText(timeFormat);

        return listView;

    }

    private int getMagnitudeColor(double magnitude) {
        int colorID;
        int mag = (int) Math.floor(magnitude);
        switch (mag) {
            case 0:
            case 1:
                colorID = R.color.magnitude1;
                break;
            case 2:
                colorID = R.color.magnitude2;
                break;
            case 3:
                colorID = R.color.magnitude3;
                break;
            case 4:
                colorID = R.color.magnitude4;
                break;
            case 5:
                colorID = R.color.magnitude5;
                break;
            case 6:
                colorID = R.color.magnitude6;
                break;
            case 7:
                colorID = R.color.magnitude7;
                break;
            case 8:
                colorID = R.color.magnitude8;
                break;
            case 9:
                colorID = R.color.magnitude9;
                break;
            default:
                colorID = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), colorID);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMag(double mag) {
        DecimalFormat format = new DecimalFormat("0.0");
        String output = format.format(mag);
        return output;
    }
}
