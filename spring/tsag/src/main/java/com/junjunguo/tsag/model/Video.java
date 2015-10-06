package com.junjunguo.tsag.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@Entity
@Table(name = "video")
public class Video {
    private long id;
    /**
     * video title description path/filename.extension
     */
    private String title, description, path, filename, extension;
    /**
     * Geographic coordinate
     */
    private double latitude, longitude, altitude;
    /**
     * upload timestamp
     */
    private Calendar timestamp;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(nullable = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(nullable = false)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Column(nullable = false)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Column(nullable = false)
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Column
    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Column(nullable = false)
    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }
}
