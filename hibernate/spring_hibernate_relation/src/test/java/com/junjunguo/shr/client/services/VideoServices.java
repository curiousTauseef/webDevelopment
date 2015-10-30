package com.junjunguo.shr.client.services;

import com.junjunguo.shr.client.model.Video;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface VideoServices {

    List<Video> listAllVideos();

    Video getVideoById(long id);

    List<Video> getVideosByEmail(String email);

    List<Video> getVideosByTitle(String title);

    String createVideo(Video video);

    String updateVideo(Video video);

    String deleteVideoById(long id);
}
