package com.junjunguo.aeep.backend.model;

import com.google.appengine.api.datastore.GeoPt;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 11, 2016.
 */
public class QueryWrapper {
    private double radius;
    private GeoPt center;
    private List<Tag> tags;

    /**
     * Instantiates a new Query wrapper.
     */
    public QueryWrapper() {
    }

    /**
     * Instantiates a new Query wrapper.
     * @param radius the radius
     * @param center the center
     */
    public QueryWrapper(double radius, GeoPt center) {
        this(radius, center, new ArrayList<Tag>());
    }

    /**
     * Instantiates a new Query wrapper.
     * @param radius the radius
     * @param center the center
     * @param tags   the tags
     */
    public QueryWrapper(double radius, GeoPt center, List<Tag> tags) {
        this.radius = radius;
        this.center = center;
        this.tags = tags;
    }

    /**
     * Gets radius.
     * @return the radius in meters
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius.
     * @param radius the radius in meters
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Gets center.
     * @return the center
     */
    public GeoPt getCenter() {
        return center;
    }

    /**
     * Sets center.
     * @param center the center
     */
    public void setCenter(GeoPt center) {
        this.center = center;
    }

    /**
     * Gets tags.
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
