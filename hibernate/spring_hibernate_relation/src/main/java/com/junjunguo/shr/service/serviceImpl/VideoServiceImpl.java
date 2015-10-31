package com.junjunguo.shr.service.serviceImpl;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

import com.junjunguo.shr.dao.LocationDao;
import com.junjunguo.shr.dao.TagDao;
import com.junjunguo.shr.dao.UserDao;
import com.junjunguo.shr.dao.VideoDao;
import com.junjunguo.shr.model.Location;
import com.junjunguo.shr.model.Tag;
import com.junjunguo.shr.model.User;
import com.junjunguo.shr.model.Video;
import com.junjunguo.shr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao    videoDao;
    @Autowired
    private TagDao      tagDao;
    @Autowired
    private UserDao     userDao;
    @Autowired
    private LocationDao locationDao;

    public Video findById(long id) {
        return videoDao.findById(id);
    }

    public List<Video> findByEmail(String email) {
        return videoDao.findByEmail(email);
    }

    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    public void addVideo(Video video) {
        System.out.println("\n@@video service : " + video + "@@\n");
        List<Tag> gTags = video.getTags();
        System.out.println("\n@@video service g tags: " + gTags + "@@\n");
        List<Tag> aTags = new ArrayList<Tag>();
        for (Tag tg : gTags) {
            Tag t = tagDao.findByLabel(tg.getLabel());
            if (t == null) {
                Tag nt = new Tag(tg.getLabel());
                aTags.add(nt);
            } else {
                aTags.add(t);
            }
        }
        video.setTags(aTags);

        User user = video.getOwner();
        System.out.println("\n@@video service user: " + user + " video: " + video + "@@\n");
        if (user != null) {
            User u = userDao.findByEmail(user.getEmail());
            if (u == null) {
                video.setOwner(new User(user.getName(), user.getEmail(), user.getPassword(), user.getCountry(),
                        user.getGender(), user.getBirth()));
            } else {
                video.setOwner(u);
            }
        }

        Location lo = video.getLocation();
        System.out.println("\n@@video service lo: " + lo + " video: " + video + "@@\n");
        if (lo != null) {
            Location l = locationDao.findByLocation(lo);
            System.out.println("\n@@video service lo: " + lo + " find l: " + l + "@@\n");
            if (l == null) {
                video.setLocation(new Location(lo.getLatitude(), lo.getLongitude(), lo.getAltitude()));
            } else {
                video.setLocation(l);
            }
        }
        System.out.println("\n@@video service video: " + video + "@@\n");
        try {
            videoDao.saveVideo(video);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void updateVideo(Video video) {
        videoDao.saveVideo(video);
    }

    public void deleteVideoById(long id) {
        videoDao.deleteVideoById(id);
    }

    public List<Video> findAllVideos() {
        return videoDao.findAllVideos();
    }

    public boolean isVideoExist(long id) {
        return videoDao.findById(id) != null;
    }

    public List<Video> findByTag(long id) {
        return videoDao.findByTag(id);
    }
}