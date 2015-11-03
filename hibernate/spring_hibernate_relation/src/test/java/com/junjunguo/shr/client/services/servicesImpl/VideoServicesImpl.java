package com.junjunguo.shr.client.services.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junjunguo.shr.client.model.Location;
import com.junjunguo.shr.client.model.Video;
import com.junjunguo.shr.client.services.VideoServices;
import com.junjunguo.shr.client.util.Constant;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public String uploadVideo(Video video) {
        RestTemplate restTemplate = new RestTemplate();
        String       url          = REST_SERVICE_URI + "upload";
        String       path         = video.getFilePath();
        String       sv           = videoToJson(video);
        if (sv.startsWith("Error")) {
            return sv;
        }

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.add("file", new FileSystemResource(path));
        map.add("video", videoToJson(video));


//        try {
//            HttpClient client = new DefaultHttpClient();
//            HttpPost post = new HttpPost(url);
//            MultipartEntity entity = new MultipartEntity();
//            entity.addPart("file", new FileBody(new File(path)));
//            entity.addPart("video", new StringBody(sv, "text/plain",
//                    Charset.forName("UTF-8")));
//            post.setEntity(entity);
//            HttpResponse response = client.execute(post);
//            log(" response ; " + response.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


                try {

                    // Message Converters
                    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
                    messageConverters.add(new FormHttpMessageConverter());
                    messageConverters.add(new SourceHttpMessageConverter());
                    messageConverters.add(new StringHttpMessageConverter());
                    messageConverters.add(new MappingJackson2HttpMessageConverter());

                    // RestTemplate
                    RestTemplate template = new RestTemplate();
                    template.setMessageConverters(messageConverters);

                    template.postForObject(REST_SERVICE_URI + "/upload", map, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        //        HttpHeaders headers = new HttpHeaders();
        //        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //
        //        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
        //                new HttpEntity<LinkedMultiValueMap<String, Object>>(
        //                        map, headers);
        //        ResponseEntity<String> result = restTemplate.exchange(
        //                REST_SERVICE_URI + "upload", HttpMethod.POST, requestEntity,
        //                String.class);


        //                HttpClient httpClient = new DefaultHttpClient();
        //                org.apache.http.HttpEntity entity = MultipartEntityBuilder
        //                        .create()
        //                        .addTextBody("video", sv)
        //                        .addBinaryBody("file", new File(path), ContentType.create("application/octet-stream"),
        //                                "filename")
        //                        .addTextBody("tos", "agree")
        //                        .build();
        //
        //                HttpPost httpPost = new HttpPost(REST_SERVICE_URI + "upload");
        //                httpPost.setEntity(entity);
        //                HttpResponse response = null;
        //                try {
        //                    response = httpClient.execute(httpPost);
        //                } catch (IOException e) {
        //                    e.printStackTrace();
        //                }
        //                org.apache.http.HttpEntity result = response.getEntity();
        //                log("result " + result.toString());


        //        HttpClient client = new DefaultHttpClient();
        //        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        //
        //        HttpPost        post   = new HttpPost(REST_SERVICE_URI + "/upload");
        //        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        //
        //        // For File parameters
        //        entity.addPart("file", new FileBody(((File) new File(path)), "application/zip"));
        //
        //        try {
        //            // For usual String parameters
        //            entity.addPart("video", new StringBody(sv, "text/plain",
        //                    Charset.forName("UTF-8")));
        //
        //            post.setEntity(entity);
        //
        //            String response = EntityUtils.toString(client.execute(post).getEntity(), "UTF-8");
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //
        //        client.getConnectionManager().shutdown();

        //        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        //        formHttpMessageConverter.setCharset(Charset.forName("UTF8"));
        //
        //        RestTemplate restTemplate = new RestTemplate();
        //
        //
        //        restTemplate.getMessageConverters().add(formHttpMessageConverter);
        //        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        //        //        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(1000 * 30);
        //        //        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(1000 * 30);
        //        MultiValueMap<String, Object> map    = new LinkedMultiValueMap<String, Object>();
        //        ObjectMapper                  mapper = new ObjectMapper();
        //        try {
        //            if (path.contains(".")) {
        //                String extension = path.substring(path.indexOf('.') + 1);
        //                video.setFileExtension(extension);
        //            }
        //            //            map.add("file", getFileAsString(path));
        //            map.add("video", mapper.writeValueAsString(video));
        //            map.add("file", new FileSystemResource(path));
        //        } catch (JsonProcessingException e) {
        //            e.printStackTrace();
        //        }
        //        HttpHeaders imageHeaders = new HttpHeaders();
        //        imageHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        //
        //        HttpEntity<MultiValueMap<String, Object>> mapHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(map,
        //                imageHeaders);
        //
        //
        //        restTemplate.exchange(REST_SERVICE_URI + "/upload", HttpMethod.POST, mapHttpEntity, String.class);
        return "su";
    }

    private String videoToJson(Video video) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(video);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error : " + e.getMessage();
        }
    }

    public String uploadVideo_(Video video) {
        String                   path                     = video.getFilePath();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.setCharset(Charset.forName("UTF8"));

        RestTemplate restTemplate = new RestTemplate();


        restTemplate.getMessageConverters().add(formHttpMessageConverter);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        //        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(1000 * 30);
        //        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(1000 * 30);
        MultiValueMap<String, Object> map    = new LinkedMultiValueMap<String, Object>();
        ObjectMapper                  mapper = new ObjectMapper();
        try {
            if (path.contains(".")) {
                String extension = path.substring(path.indexOf('.') + 1);
                video.setFileExtension(extension);
            }
            //            map.add("file", getFileAsString(path));
            map.add("video", mapper.writeValueAsString(video));
            map.add("file", new FileSystemResource(path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpHeaders imageHeaders = new HttpHeaders();
        imageHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> mapHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(map,
                imageHeaders);


        restTemplate.exchange(REST_SERVICE_URI + "/upload", HttpMethod.POST, mapHttpEntity, String.class);
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
