package com.junjunguo.shr.service;

import com.junjunguo.shr.service.model.Video;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface VideoServices {

    void listAllVideos();

    Video getVideoById(int id);

    List<Video> getVideosByEmail(String email);

    List<Video> getVideosByTitle(String title);

    String createVideo(Video video);

    String updateVideo(Video video);

    String deleteVideoById(int id);
}
