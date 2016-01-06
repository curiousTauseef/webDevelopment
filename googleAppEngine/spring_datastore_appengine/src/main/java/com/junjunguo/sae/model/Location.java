package com.junjunguo.sae.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * This file is part of spring_datastore_appengine.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 04/01/16.
 */
@Entity
public class Location {
    @Id
    private long   id;
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(long id, double latitude, double longitude) {
        this(latitude, longitude);
        this.id = id;
    }
}
