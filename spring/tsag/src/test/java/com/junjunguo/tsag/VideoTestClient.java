package com.junjunguo.tsag;

import com.junjunguo.tsag.model.Video;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class VideoTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/video/";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllVideos() {
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
    private static void getVideoById() {
        System.out.println("Testing getVideo by id API----------");
        RestTemplate restTemplate = new RestTemplate();
        Video video = restTemplate.getForObject(REST_SERVICE_URI + "/id/1", Video.class);
        System.out.println(video);
    }

    /* GET */
    private static void getVideosByEmail() {
        System.out.println("Testing getVideosByEmail API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> videosMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/email/ola@a.a", List.class);

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
    private static void getVideosByTitle() {
        System.out.println("Testing getVideosBy title API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> videosMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/title/20sag", List.class);

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
    private static void createVideo() {
        System.out.println("Testing create Video API----------");
        RestTemplate restTemplate = new RestTemplate();
        cvideo = new Video("20sag", "trondheim", "trondheim fest test created", "file path", "videoj", "mkv",
                           "johan@a.a", 60.11, 11.99, 0);
        System.out.println("new video: " + cvideo);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI, cvideo, Video.class);
        System.out.println("Location : " + uri.toASCIIString());
    }


    /* PUT */
    private static void updateVideo() {
        System.out.println("Testing update Video API----------");
        RestTemplate restTemplate = new RestTemplate();
        Video video = new Video("20sag", "trondheim, fest", "trondheim fest test updated", "file path", "videoj", "mkv",
                                "johan@a.a", 60.11, 11.99, 0);
        video.setId(cvideo.getId());
        restTemplate.put(REST_SERVICE_URI + cvideo.getId(), video);
        System.out.println(video);
    }

    /* DELETE */
    private static void deleteVideo() {
        System.out.println("Testing delete Video API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "1");
        System.out.println("deleted video with id: 1");
    }

    public static void main(String args[]) {
        listAllVideos();
        getVideoById();//by name
        getVideosByTitle();
        getVideosByEmail();
        createVideo();
        listAllVideos();
        updateVideo();
        listAllVideos();
        deleteVideo();
        listAllVideos();
        listAllVideos();
    }
}