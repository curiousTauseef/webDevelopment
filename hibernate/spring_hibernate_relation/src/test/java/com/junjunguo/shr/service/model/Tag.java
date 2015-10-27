package com.junjunguo.shr.service.model;

import javax.persistence.*;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public class Tag {
    /**
     * tag id
     */
    private int         id;
    private String      label;
    private List<Video> videos;

    public Tag(String label) {
        this.label = label;
    }

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag() {
    }

    /**
     * Sets new tag id.
     *
     * @param id New value of tag id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets tag id.
     *
     * @return Value of tag id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets label.
     *
     * @return Value of label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets new label.
     *
     * @param label New value of label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets videos.
     *
     * @return Value of videos.
     */
    public List<Video> getVideos() { return videos; }

    /**
     * Sets new videos.
     *
     * @param videos New value of videos.
     */
    public void setVideos(List<Video> videos) { this.videos = videos; }
}