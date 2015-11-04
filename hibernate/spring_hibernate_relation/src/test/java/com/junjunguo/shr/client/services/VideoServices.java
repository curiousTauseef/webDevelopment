package com.junjunguo.shr.client.services;

import com.junjunguo.shr.client.model.Location;
import com.junjunguo.shr.client.model.Video;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface VideoServices {
    /**
     * @return list of all videos at server
     */
    List<Video> listAllVideos();

    /**
     * @param id video id
     * @return video by given id
     */
    Video getVideoById(long id);

    String fetchVideoFile(long videoId);

    /**
     * @param email owners email
     * @return videos owned by the owner
     */
    List<Video> getVideosByEmail(String email);

    /**
     * @param title video title
     * @return a list of videos with the given title
     */
    List<Video> getVideosByTitle(String title);

    /**
     * @param video going to be created
     * @return feedback message about the process
     */
    String createVideo(Video video);

    /**
     * @param video going to be created and the path pointed file will be uploaded as this particular video
     * @return feedback message about the process
     */
    String uploadVideo(Video video);

    /**
     * @param video going to be created
     * @return feedback message about the process
     */
    //    String uploadVideo(Video video);

    /**
     * @param video the video with new information
     * @return feedback message about the process
     */
    String updateVideo(Video video);

    /**
     * @param id video id
     * @return feedback message about the process
     */
    String deleteVideoById(long id);

    /**
     * @param location the center location
     * @param boundary the boundary: length of 1 degree of latitude on the sphere is 111.2 km
     * @return videos near by given boundary
     */
    List<Video> findNearBy(Location location, double boundary);
}
