package com.junjunguo.shr;

import com.junjunguo.shr.model.Video;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 26/10/15.
 */
public class ClientVideo {
    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/video/";

    /* GET */
    @SuppressWarnings("unchecked")
    public  static void listAllVideos() {
        System.out.println("Testing listAllVideos API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> videosMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/list/", List.class);

        if (videosMap != null) {
            for (LinkedHashMap<String, Object> map : videosMap) {
                System.out.println("Video :  title=" + map.get("title") +
                                   ", tags=" + map.get("tags") +
                                   ", id=" + map.get("id") +
                                   ", description=" + map.get("description") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileName=" + map.get("fileName") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileExtension=" + map.get("fileExtension") +
                                   ", ownerEmail=" + map.get("ownerEmail") +
                                   ", location=" + map.get("location") +
                                   ", filePath=" + map.get("filePath"));
                ;
            }
        } else {
            System.out.println("No video exist----------");
        }
    }

    /* GET */
    public  static void getVideoById(int id) {
        System.out.println("Testing getVideo by id API----------");
        RestTemplate restTemplate = new RestTemplate();
        Video        video        = restTemplate.getForObject(REST_SERVICE_URI + "/id/" + id, Video.class);
        System.out.println(video);
    }

    /* GET */
    public  static void getVideosByEmail(String email) {
        System.out.println("Testing getVideosByEmail API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> videosMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/email/" + email, List.class);

        if (videosMap != null) {
            for (LinkedHashMap<String, Object> map : videosMap) {
                System.out.println("Video :  title=" + map.get("title") +
                                   ", tags=" + map.get("tags") +
                                   ", id=" + map.get("id") +
                                   ", description=" + map.get("description") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileName=" + map.get("fileName") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileExtension=" + map.get("fileExtension") +
                                   ", ownerEmail=" + map.get("ownerEmail") +
                                   ", location=" + map.get("location") +
                                   ", filePath=" + map.get("filePath"));
            }
        } else {
            System.out.println("No video exist----------");
        }
    }

    /* GET */
    public  static void getVideosByTitle(String title) {
        System.out.println("Testing getVideosBy title API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> videosMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/title/" + title, List.class);

        if (videosMap != null) {
            for (LinkedHashMap<String, Object> map : videosMap) {
                System.out.println("Video :  title=" + map.get("title") +
                                   ", tags=" + map.get("tags") +
                                   ", id=" + map.get("id") +
                                   ", description=" + map.get("description") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileName=" + map.get("fileName") +
                                   ", timeStamp=" + map.get("timeStamp") +
                                   ", fileExtension=" + map.get("fileExtension") +
                                   ", ownerEmail=" + map.get("ownerEmail") +
                                   ", location=" + map.get("location") +
                                   ", filePath=" + map.get("filePath"));
            }
        } else {
            System.out.println("No video exist----------");
        }
    }

    static Video cvideo;

    /* POST */
    public  static void createVideo(Video video) {
        System.out.println("Testing create Video API----------");
        RestTemplate restTemplate = new RestTemplate();

        System.out.println("new video: " + video);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI, video, Video.class);
        System.out.println("Location : " + uri.toASCIIString());
    }


    /* PUT */
    public  static void updateVideo(Video video) {
        System.out.println("Testing update Video API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_SERVICE_URI + video, video);
        System.out.println(video);
    }

    /* DELETE */
    public  static void deleteVideo() {
        System.out.println("Testing delete Video API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "1");
        System.out.println("deleted video with id: 1");
    }
}
