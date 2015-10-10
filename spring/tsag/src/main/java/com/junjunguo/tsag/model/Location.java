package com.junjunguo.tsag.model;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 10, 2015.
 */
public class Location {
    private double latitude, logitude, altitude;

    public Location(double latitude, double logitude, double altitude) {
        this.latitude = latitude;
        this.logitude = logitude;
        this.altitude = altitude;
    }

    public Location(double latitude, double logitude) {
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public Location() {
        this(0, 0, 0);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
