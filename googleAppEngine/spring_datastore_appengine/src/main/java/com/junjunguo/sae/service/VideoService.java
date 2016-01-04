package com.junjunguo.sae.service;

import com.junjunguo.sae.model.Location;
import com.junjunguo.sae.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public interface VideoService {

    /**
     * @param id video id
     * @return Video object
     */
    Video findById(long id);

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
     * @param video object: save or update this video
     * @param file  File
     */
    String addVideo(Video video, MultipartFile file);

    void updateVideo(Video video);

    boolean isVideoExist(long id);

    /**
     * @param id delete video by the given id
     */
    void deleteVideoById(long id);

    /**
     * @return a List of videos
     */
    List<Video> findAllVideos();

    /**
     * @param id tag id
     * @return list of video with given tag
     */
    List<Video> findByTag(long id);

    /**
     * @param location the center location
     * @param boundary the boundary: length of 1 degree of latitude on the sphere is 111.2 km
     * @return videos near by given boundary
     */
    List<Video> findNearBy(Location location, double boundary);

    /**
     * @param videoId video id
     * @return video file path
     */
    String getFileFor(long videoId);
}
