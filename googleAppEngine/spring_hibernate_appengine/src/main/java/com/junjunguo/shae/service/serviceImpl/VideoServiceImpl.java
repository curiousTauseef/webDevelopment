package com.junjunguo.shae.service.serviceImpl;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

import com.junjunguo.shae.dao.LocationDao;
import com.junjunguo.shae.dao.TagDao;
import com.junjunguo.shae.dao.UserDao;
import com.junjunguo.shae.dao.VideoDao;
import com.junjunguo.shae.model.Location;
import com.junjunguo.shae.model.Tag;
import com.junjunguo.shae.model.User;
import com.junjunguo.shae.model.Video;
import com.junjunguo.shae.service.VideoService;
import com.junjunguo.shae.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    private FileHandler fileHandler = new FileHandler();

    public Video findById(long id) {
        return videoDao.findById(id);
    }

    public List<Video> findByEmail(String email) {
        return videoDao.findByEmail(email);
    }

    public List<Video> findByTitle(String title) {
        return videoDao.findByTitle(title);
    }

    public String addVideo(Video video, MultipartFile mFile) {
        log("\n@@video service : " + video + "@@\n");
        List<Tag> gTags = video.getTags();
        log("\n@@video service g tags: " + gTags + "@@\n");
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
        log("\n@@video service user: " + user + " video: " + video + "@@\n");
        if (user != null) {
            User u = userDao.findByEmail(user.getEmail());
            if (u == null) {
                User createU = new User(user.getName(), user.getEmail(), user.getPassword(), user.getCountry(),
                        user.getGender(), user.getBirth());
                userDao.saveUser(createU);
            }
            u = userDao.findByEmail(user.getEmail());
            video.setOwner(u);
        }

        Location lo = video.getLocation();
        log("\n@@video service lo: " + lo + " video: " + video + "@@\n");
        if (lo != null) {
            Location l = locationDao.findByLocation(lo);
            log("\n@@video service lo: " + lo + " find l: " + l + "@@\n");
            if (l == null) {
                video.setLocation(new Location(lo.getLatitude(), lo.getLongitude(), lo.getAltitude()));
            } else {
                video.setLocation(l);
            }
        }
        log("\n@@video service video: " + video + "@@\n");
        String path = fileHandler.save(video, mFile);
        try {
            if (!path.startsWith("Error")) {
                video.setFilePath(path);
                video.setHasVideo(true);
                videoDao.saveVideo(video);
                return "add video succeed.";
            } else {
                return path;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return e.getMessage();
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

    public List<Video> findNearBy(Location location, double boundary) {
        return videoDao.findNearBy(location, boundary);
    }

    public String getFileFor(long videoId) {
        return findById(videoId).getFilePath();
    }


    public void log(String s) {
        //        log(this.getClass().getSimpleName() + "- - - - - - " + s);
    }
}
