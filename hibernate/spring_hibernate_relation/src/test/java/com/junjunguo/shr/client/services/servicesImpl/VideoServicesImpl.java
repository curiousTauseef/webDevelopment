package com.junjunguo.shr.client.services.servicesImpl;

import com.junjunguo.shr.client.model.Location;
import com.junjunguo.shr.client.model.Video;
import com.junjunguo.shr.client.services.VideoServices;
import com.junjunguo.shr.client.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
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
    public List<Video> listAllVideos() {
        List<Video>  videos       = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            videos = restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no video found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return videos;
    }

    /* GET */
    public Video getVideoById(long id) {
        Video        video        = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            video = restTemplate.getForObject(REST_SERVICE_URI + "/id/" + id, Video.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("video with id: {" + id + "} not found !");
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

    @SuppressWarnings("unchecked")
    public List<Video> findNearBy(Location location, double boundary) {
        List<Video>  videos       = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            videos = restTemplate
                    .getForObject(REST_SERVICE_URI +
                                  "/location/nearby/" +
                                  location.getLatitude() + "/" +
                                  location.getLongitude() + "/" +
                                  location.getAltitude() + "/" +
                                  boundary, List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no videos found !");
            } else {
                System.out.println("oops! error occurred (findNearBy)! " + e.getMessage());
            }
        }
        return videos;
    }

    /* POST */
    public String createVideo(Video video, String path) {
        RestTemplate                  restTemplate = new RestTemplate();
        String                        message;
        MultiValueMap<String, Object> parts        = new LinkedMultiValueMap<String, Object>();
        parts.add("video", video);
        parts.add("file", getFileAsString(path));
        try {
            log("s: " + parts.toString().substring(0, 1000));
            log("f " + parts.getFirst("file").toString().length());
            URI uri =
//                    restTemplate.postForLocation(REST_SERVICE_URI, video, Video.class);
                                restTemplate.postForLocation(REST_SERVICE_URI, parts, MultiValueMap.class);
            System.out.println("Location : " + uri.toASCIIString());
            message = "create video: " + video + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "video: {" + video.toString() + "} already exist !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }


    /* PUT */
    public String updateVideo(Video video) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, video);
            message = "update video: " + video + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                message = "user: {" + video + "} not found !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* DELETE */
    public String deleteVideoById(long id) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + id + "/");
            message = "delete video with id: " + id + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                message = "video with id: {" + id + "} not found !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    private String getFileAsString(String path) {
        try {
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getFileAsBytes(String path) {
        try {
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "- - - - - - " + s);
    }
}
