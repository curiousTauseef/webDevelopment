package com.junjunguo.shr.service.serviceImpl;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

import com.junjunguo.shr.dao.VideoDao;
import com.junjunguo.shr.model.Video;
import com.junjunguo.shr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("videoservice")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;

    public Video findById(int id) {
        return videoDao.findById(id);
    }

    public List<Video> findByEmail(String email) {
        return videoDao.findByEmail(email);
    }

    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    public List<Video> findByTag(String tag) {
        return videoDao.findByTag(tag);
    }

    public List<Video> findByTags(List<String> tags) {
        return videoDao.findByTags(tags);
    }

    public void addVideo(Video video) {
        videoDao.saveVideo(video);
    }

    public void updateVideo(Video video) {
        videoDao.saveVideo(video);
    }

    public void deleteVideoById(int id) {
        videoDao.deleteVideoById(id);
    }

    public List<Video> findAllVideos() {
        return videoDao.findAllVideos();
    }

    public boolean hasVideo(int id) {
        return videoDao.hasVideo(id);
    }
}
