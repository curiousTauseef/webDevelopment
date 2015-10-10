package com.junjunguo.tsag.service;

import com.junjunguo.tsag.model.Video;

import java.util.List;


public interface VideoService {

    Video findById(int id);

    List<Video> findByEmail(String email);

    List<Video> findByTitle(String title);

    List<Video> findByTags(String tag);

    void addVideo(Video video);

    void updateVideo(Video video);

    void deleteVideoById(int id);

    List<Video> findAllVideos();

    boolean isVideoExist(int id);

}
