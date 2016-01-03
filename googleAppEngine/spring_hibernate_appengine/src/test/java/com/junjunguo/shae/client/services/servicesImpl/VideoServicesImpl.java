package com.junjunguo.shae.client.services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junjunguo.shae.client.model.Location;
import com.junjunguo.shae.client.model.Video;
import com.junjunguo.shae.client.services.VideoServices;
import com.junjunguo.shae.client.util.Constant;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String fetchVideoFile(long videoId) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new ByteArrayHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    REST_SERVICE_URI + "download/" + videoId,
                    HttpMethod.GET, entity, byte[].class, "1");

            if (response.getStatusCode() == HttpStatus.OK) {
                String s = response.getHeaders().getFirst("Content-Disposition");
                s = s.substring(s.indexOf("filename=") + 10, s.lastIndexOf('"'));
                Files.write(Paths.get(Constant.SAVE_TO, s), response.getBody());
                return "Succeed! video saved at: " + Constant.SAVE_TO + s;
            }
            return "Error occurred: " + response.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    /* POST */

    /**
     * @param video video to be created
     * @return feedback message
     */
    public String createVideo(Video video) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, video, Video.class);
            message = "create video: " + video + " succeed";
            System.out.println("Location : " + uri.toASCIIString());
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "user: {" + video.toString() + "} already exist !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /**
     * use  MultiPartFile request to
     * <p/>
     * upload video file and create a video object at server side
     * <p/>
     * the tags, and owner in the video object will be also created if not exist
     * <p/>
     * if upload file fail: the system will still try to create the user if not exist in the server
     *
     * @param video going to be created
     * @return feedback message
     */
    public String uploadVideo(Video video) {
        String path = video.getFilePath();
        if (path.contains(".")) {
            String extension = path.substring(path.lastIndexOf('.') + 1);
            video.setFileExtension(extension);
        }
        String sv = videoToJson(video);
        if (sv.startsWith("Error")) {
            return sv;
        }
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.add("file", new FileSystemResource(path));
        map.add("video", sv);
        RestTemplate restTemplate = new RestTemplate();
        try {
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new SourceHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter());
            messageConverters.add(new MappingJackson2HttpMessageConverter());
            restTemplate.setMessageConverters(messageConverters);
            return restTemplate.postForObject(REST_SERVICE_URI + "/upload", map, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    /* POST */

    /**
     * this method is !test only! should not be used !
     * <p/>
     * use a normal request: convert file to string and upload it to server will decode the string to file
     * <p/>
     * upload video file and create a video object at server side
     * <p/>
     * the tags, and owner in the video object will be also created if not exist
     * <p/>
     * if upload file fail: the system will still try to create the user if not exist in the server
     *
     * @param video going to be created
     * @param path  File path going to be uploaded
     * @return feedback message
     */
    public String uploadVideoTest(Video video, String path) {
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


    /**
     * @param video convert video object to Json string
     * @return json string
     */
    private String videoToJson(Video video) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(video);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error : " + e.getMessage();
        }
    }

    /**
     * @param path file path
     * @return a string constructed by decoding the array of bytes
     */
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
