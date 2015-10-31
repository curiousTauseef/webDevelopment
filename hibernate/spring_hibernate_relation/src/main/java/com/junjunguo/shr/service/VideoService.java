package com.junjunguo.shr.service;

import com.junjunguo.shr.model.Video;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public interface VideoService {
    Video findById(long id);

    List<Video> findByEmail(String email);

    List<Video> findByTitle(String title);

    void addVideo(Video video);

    void updateVideo(Video video);

    void deleteVideoById(long id);

    List<Video> findAllVideos();

    boolean isVideoExist(long id);

    List<Video> findByTag(long id);
}
