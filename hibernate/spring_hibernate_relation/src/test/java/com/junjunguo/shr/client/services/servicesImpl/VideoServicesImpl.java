package com.junjunguo.shr.client.services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junjunguo.shr.client.model.Location;
import com.junjunguo.shr.client.model.Video;
import com.junjunguo.shr.client.services.VideoServices;
import com.junjunguo.shr.client.util.Constant;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
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

    public String createVideo_(Video video, String path) {
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        //        formHttpMessageConverter.setCharset(Charset.forName("UTF8"));

        RestTemplate restTemplate = new RestTemplate();


        restTemplate.getMessageConverters().add(formHttpMessageConverter);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("name", "1");
        map.add("file", new FileSystemResource(path));

        HttpHeaders imageHeaders = new HttpHeaders();
        imageHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> mapHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(map,
                imageHeaders);


        restTemplate.exchange(REST_SERVICE_URI + "/e", HttpMethod.POST, mapHttpEntity, String.class);
        return "su";
    }

    /* POST */
    public String createVideo(Video video, String path) {
        String                              message;
        RestTemplate                        restTemplate = new RestTemplate();
        LinkedMultiValueMap<String, Object> map          = new LinkedMultiValueMap();
        ObjectMapper                        mapper       = new ObjectMapper();
        try {
            if (path.contains(".")) {
                String extension = path.substring(path.indexOf('.') + 1);
                video.setFileExtension(extension);
            }
            map.add("file", getFileAsString(path));
            map.add("video", mapper.writeValueAsString(video));
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(1000 * 30);
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(1000 * 30);

            message = restTemplate.postForObject(REST_SERVICE_URI + "", map, String.class);
            //            message = "create video: " + video + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "video: {" + video.toString() + "} already exist !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            message = "oops! error occurred! " + e.getMessage();
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
