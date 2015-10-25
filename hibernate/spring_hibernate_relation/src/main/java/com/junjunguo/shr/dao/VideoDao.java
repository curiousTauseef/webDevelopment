package com.junjunguo.shr.dao;

import com.junjunguo.shr.model.Location;
import com.junjunguo.shr.model.Video;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public interface VideoDao {

    /**
     * @param id video id
     * @return Video object
     */
    Video findById(int id);

    /**
     * @param email video email
     * @return List of Video object
     */
    List<Video> findByEmail(String email);

    /**
     * @param name video name
     * @return Video object
     */
    List<Video> findByTitle(String name);

    /**
     * @param tag video tag
     * @return List of Video object
     */
    List<Video> findByTag(String tag);

    /**
     * @param tags video tags
     * @return List of Video object
     */
    List<Video> findByTags(List<String> tags);

    /**
     * @param location video Location
     * @return List of Video object
     */
    List<Video> findByLocation(Location location);

    /**
     * @param video object: save or update this video
     */
    void saveVideo(Video video);

    /**
     * @param id delete video by the given id
     */
    void deleteVideoById(int id);

    /**
     * @return a List of videos
     */
    List<Video> findAllVideos();

    /**
     * @return boolean has video or not
     */
    boolean hasVideo(int id);
}
