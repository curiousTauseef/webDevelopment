package com.junjunguo.shr.service.servicesImpl;

import com.junjunguo.shr.service.VideoServices;
import com.junjunguo.shr.service.model.Video;
import com.junjunguo.shr.service.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 26/10/15.
 */
public class VideoServicesImpl implements VideoServices {
    public final String REST_SERVICE_URI = Constant.SERVER_URL + "/video/";

    /* GET */
    @SuppressWarnings("unchecked")
    public void listAllVideos() {
        //        System.out.println("Testing listAllVideos API-----------");

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
    public Video getVideoById(int id) {
        Video        video        = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            video = restTemplate.getForObject(REST_SERVICE_URI + "/id/" + id, Video.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("video: {" + video + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return video;
    }


    /* GET */
    @SuppressWarnings("unchecked")
    public List<Video> getVideosByEmail(String email) {
        List<Video>  videos       = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            videos = restTemplate
                    .getForObject(REST_SERVICE_URI + "/email/" + email, List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no videos owned by: {" + email + "} !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return videos;
    }

    /* GET */
    @SuppressWarnings("unchecked")
    public List<Video> getVideosByTitle(String title) {
        List<Video>  videos       = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            videos = restTemplate
                    .getForObject(REST_SERVICE_URI + "/title/" + title, List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no videos found with title: {" + title + "} !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return videos;
    }

    /* POST */
    public String createVideo(Video video) {
        RestTemplate restTemplate = new RestTemplate();
        String       message      = "";

        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, video, Video.class);
            System.out.println("Location : " + uri.toASCIIString());
            message = "succeed";
            //            System.out.println("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "video: {" + video.toString() + "} already exist !";
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }


    /* PUT */
    public String updateVideo(Video video) {
        String       message      = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, video);
            message = "succeed";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                message = "user: {" + video + "} not found !";
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* DELETE */
    public String deleteVideoById(int id) {
        String       message      = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + id + "/");
            //            System.out.println("user with email: " + email + " deleted");
            message = "succeed";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                //                System.out.println("user with email: {" + email + "} not found !");
                message = "video with id: {" + id + "} not found !";
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "\"oops! error occurred! \" + e.getMessage()";
            }
        }
        return message;
    }

}
