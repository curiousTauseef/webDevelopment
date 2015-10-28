package com.junjunguo.shr.service.serviceImpl;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

import com.junjunguo.shr.dao.TagDao;
import com.junjunguo.shr.dao.UserDao;
import com.junjunguo.shr.dao.VideoDao;
import com.junjunguo.shr.model.Tag;
import com.junjunguo.shr.model.User;
import com.junjunguo.shr.model.Video;
import com.junjunguo.shr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("videoservice")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private TagDao   tagDao;

    @Autowired
    private UserDao userDao;

    public Video findById(int id) {
        return videoDao.findById(id);
    }

    public List<Video> findByEmail(String email) {
        return videoDao.findByEmail(email);
    }

    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    public void addVideo(Video video) {
        System.out.println("add video video service impl " + video);
        List<Tag> tags = video.getTags();
        List<Tag> tgs  = new ArrayList<Tag>();
        for (Tag tg : tags) {
            System.out.println("------ " + tg);
            Tag t = tagDao.findByLabel(tg.getLabel());
            System.out.println("------ " + t);
            if (t == null) {
                Tag nt = new Tag(tg.getLabel());
                tgs.add(nt);
            } else {
                tgs.add(t);
            }
        }
        User user = video.getOwner();
        if (user != null) {
            User u = userDao.findByEmail(user.getEmail());
            if (u == null) {
                video.setOwner(new User(user.getName(), user.getEmail(), user.getPassword(), user.getCountry(),
                        user.getGender(), user.getBirth()));
            } else {
                video.setOwner(u);
            }

        }
        video.setTags(tgs);
        System.out.println("\n video set tags \n");
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
