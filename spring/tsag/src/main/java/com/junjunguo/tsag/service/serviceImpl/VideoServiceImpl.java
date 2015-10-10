package com.junjunguo.tsag.service.serviceImpl;

import com.junjunguo.tsag.model.Location;
import com.junjunguo.tsag.model.Video;
import com.junjunguo.tsag.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("videoservice")
@Transactional
public class VideoServiceImpl implements VideoService {

    private static List<Video> videos;

    static {
        videos = populateDummyvideos();
    }


    public Video findById(int id) {
        for (Video video : videos) {
            if (video.getId() == id) {
                return video;
            }
        }
        return null;
    }

    public List<Video> findByEmail(String email) {
        List<Video> v = new ArrayList<Video>();
        for (Video video : videos) {
            if (video.getOwnerEmail().equalsIgnoreCase(email)) {
                v.add(video);
            }
        }
        return v;
    }

    public List<Video> findByTitle(String title) {
        List<Video> v = new ArrayList<Video>();
        for (Video video : videos) {
            if (video.getTitle().contains(title) || video.getTitle().equalsIgnoreCase(title)) {
                v.add(video);
            }
        }
        return v;
    }

    public List<Video> findByTags(String tag) {
        List<Video> v = new ArrayList<Video>();
        for (Video video : videos) {
            if (video.getTitle().contains(tag)) {
                v.add(video);
            }
        }
        return v;
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void updateVideo(Video video) {
        int index = videos.indexOf(video);
        videos.set(index, video);
    }

    public void deleteVideoById(int id) {
        for (Iterator<Video> iterator = videos.iterator(); iterator.hasNext(); ) {
            Video video = iterator.next();
            if (video.getId() == id) {
                iterator.remove();
            }
        }
    }

    public List<Video> findAllVideos() {
        return videos;
    }

    public boolean isVideoExist(int id) {
        return findById(id) != null;
    }

    private static List<Video> populateDummyvideos() {
        List<Video> videos = new ArrayList<Video>();
        videos.add(new Video("file path", "video1", "mkv", "ola@a.a", new Location(61.11, 10.12)));
        videos.add(new Video("file path", "videoo", "mkv", "ola@a.a", new Location(61.66, 10.12)));
        videos.add(new Video("file path", "videow", "mkv", "william@a.a", new Location(61.99, 10.12)));
        videos.add(new Video("file path", "videoj", "mkv", "johan@a.a", new Location(61.11, 10.99)));
        videos.add(new Video("file path", "videoj", "mkv", "johan@a.a", new Location(62.11, 10.99)));
        videos.add(new Video("20sag", "trondheim", "trondheim fest", "file path", "videoj", "mkv", "johan@a.a",
                             new Location(60.11, 10.99)));
        videos.add(new Video("20sag", "trondheim", "trondheim fest", "file path", "videoj", "mkv", "johan@a.a",
                             new Location(60.11, 11.99)));
        return videos;
    }
}
