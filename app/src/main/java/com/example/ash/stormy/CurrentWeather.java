package com.example.ash.stormy;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class CurrentWeather {

    private String locationLable;
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double precepChance;
    private String summary;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private String timeZone;


    public String getLocationLable() {

        return locationLable;
    }

    public void setLocationLable(String locationLable) {
        this.locationLable = locationLable;
    }

    public String getIcon() {
        return icon;
    }

    public int  getIconID () {
        // Possiblile icons incluse:
        // clear-day, clear-night m rain m snow, sleet, wind, fog
        //cloudy, partily-cloudy-day or partily cloudy night
        int iconId = R.drawable.clear_day;

        switch (icon){
            case "clear-day":
                iconId = R.drawable.clear_day;
                break;
            case "clear-night":
                iconId = R.drawable.clear_day;
                break;
            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
        }
        return iconId;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime () {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a"); // Date format we need to be in
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));// Set time zone
        time = time *1000; // Has to be multiplied by 100 because of the constructor takes in ms
        Date actual_date = new Date(time);// pass in the epoch time gotten from the API

        return formatter.format(actual_date);// format and convert time
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecepChance() {
        return precepChance;
    }

    public void setPrecepChance(double precepChance) {
        this.precepChance = precepChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
