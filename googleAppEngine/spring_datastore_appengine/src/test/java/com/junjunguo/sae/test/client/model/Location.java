package com.junjunguo.sae.test.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public class Location implements Serializable {
    /**
     * location id
     */
    private long   id;
    private double latitude;
    private double longitude;
    private double altitude;

    private List<Video> videos;

    public Location(double latitude, double logitude, double altitude) {
        this.latitude = latitude;
        this.longitude = logitude;
        this.altitude = altitude;
    }

    public Location(double latitude, double logitude) {
        this(latitude, logitude, 0);
    }

    public Location() {
        this(0, 0, 0);
    }

    /**
     * Gets location id.
     *
     * @return Value of location id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new location id.
     *
     * @param id New value of location id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets altitude.
     *
     * @return Value of altitude.
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Gets latitude.
     *
     * @return Value of latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets new longitude.
     *
     * @param longitude New value of longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets new altitude.
     *
     * @param altitude New value of altitude.
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Sets new latitude.
     *
     * @param latitude New value of latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return Value of longitude.
     */
    public double getLongitude() {
        return longitude;
    }


}
