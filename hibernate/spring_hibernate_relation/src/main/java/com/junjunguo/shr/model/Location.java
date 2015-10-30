package com.junjunguo.shr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Entity
@Table(name = "LOCATION",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"LATITUDE", "LONGITUDE", "ALTITUDE"})}
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
    /**
     * location id
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long    id;
    @Column(name = "LATITUDE",
            nullable = false,
            columnDefinition = "DOUBLE")
    private double latitude;
    @Column(name = "LONGITUDE",
            nullable = false,
            columnDefinition = "DOUBLE")
    private double longitude;
    @Column(name = "ALTITUDE",
            nullable = true,
            columnDefinition = "DOUBLE")
    private double altitude;

    public Location(double latitude, double logitude, double altitude) {
        this.latitude = latitude;
        this.longitude = logitude;
        this.altitude = altitude;
    }

    public Location(double latitude, double logitude) {
        this.latitude = latitude;
        this.longitude = logitude;
    }

    public Location() {
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
