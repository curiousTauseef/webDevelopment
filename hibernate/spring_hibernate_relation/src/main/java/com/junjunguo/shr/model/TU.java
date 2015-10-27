package com.junjunguo.shr.model;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public class TU extends Tag {

    private List<Video> videos;

    public TU(int id, String label, List<Video> videos) {
        super(id, label);
        setVideos(videos);
    }


    /**
     * Sets new videos.
     *
     * @param videos New value of videos.
     */
    public void setVideos(List<Video> videos) { this.videos = videos; }

    /**
     * Gets videos.
     *
     * @return Value of videos.
     */
    public List<Video> getVideos() { return videos; }
}
