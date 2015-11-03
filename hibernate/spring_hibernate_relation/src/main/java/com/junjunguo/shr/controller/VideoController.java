package com.junjunguo.shr.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junjunguo.shr.model.Location;
import com.junjunguo.shr.model.Video;
import com.junjunguo.shr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@RestController
@RequestMapping(value = "/video/")
public class VideoController {

    @Autowired VideoService videoService;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = {"/list/", "/"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Video>> listAllVideos() {
        List<Video> Videos = videoService.findAllVideos();
        if (Videos.isEmpty()) {
            return new ResponseEntity<List<Video>>(
                    HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Video>>(Videos, HttpStatus.OK);
    }


    //    -------------------Retrieve Video----------------------------------------------------

    @RequestMapping(value = {"/id/{id}/", "/id/{id}"},
                    method = RequestMethod.GET)
    public ResponseEntity<Video> getVideoById(
            @PathVariable("id")
            long id) {
        System.out.println("Fetching Video with id " + id);
        Video Video = videoService.findById(id);
        if (Video == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Video>(Video, HttpStatus.OK);
    }

    @RequestMapping(value = {"/title/{title}/", "/title/{title}"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Video>> getVideosByTitle(
            @PathVariable("title")
            String title) {
        System.out.println("Fetching Video with title " + title);
        List<Video> videos = videoService.findByTitle(title);
        if (videos == null || videos.isEmpty()) {
            System.out.println("Video with title " + title + " not found");
            return new ResponseEntity<List<Video>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }


    @RequestMapping(value = "/email/{email:.+}/",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideosByEmail(
            @PathVariable("email")
            String email) {
        System.out.println("Fetching Video with email " + email);
        List<Video> videos = videoService.findByEmail(email);
        if (videos == null || videos.isEmpty()) {
            System.out.println("Video with email " + email + " not found");
            return new ResponseEntity<List<Video>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }


    @RequestMapping(value = {"/tag/{id}/", "/tag/{id}"},
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideosByTagID(
            @PathVariable("id")
            long id) {
        System.out.println("Fetching Video with email " + id);
        List<Video> videos = videoService.findByTag(id);
        if (videos == null || videos.isEmpty()) {
            System.out.println("Video with tag id " + id + " not found");
            return new ResponseEntity<List<Video>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }


    @RequestMapping(value = {"/location/nearby/{latitude}/{longitude}/{altitude}/{boundary}"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Video>> getVideosNearby(
            @PathVariable(value = "latitude")
            double latitude,
            @PathVariable(value = "longitude")
            double longitude,
            @PathVariable(value = "altitude")
            double altitude,
            @PathVariable(value = "boundary")
            double boundary) {
        List<Video> videos = videoService.findNearBy(new Location(latitude, longitude, altitude), boundary);
        if (videos == null || videos.isEmpty()) {
            return new ResponseEntity<List<Video>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }

    //-------------------Create a Video------------------------------------------------------

    @RequestMapping(value = "",
                    method = RequestMethod.POST)
    public String createVideo(
            @RequestParam(value = "video",
                          required = false)
            String video,
            @RequestParam(value = "file",
                          required = false)
            String file) {
        log("video: \n" + video);
        Video vo;
        try {
            vo = getVideo(video);
            log("vo to string : " + vo.toString());
            if (!file.isEmpty()) {
                log("file is not empty ;) " + file.substring(0, 10));
                //            log("v to string " + vo.toString());
                log(videoService.addVideo(vo, file));
                return "Succeed";
            } else {
                return "You failed to upload " + "" + " because the file was empty.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private Video getVideo(String s) throws IOException {
        //        Video        v      = new Video();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //        mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(
        //                JsonAutoDetect.Visibility.ANY));
        return mapper.readValue(s, Video.class);
    }

    @RequestMapping(value = "/multi",
                    method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(
            @RequestParam("name")
            String name,
            @RequestParam("file")
            MultipartFile file) {
        log("e . up load called");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

    //------------------- Update a Video -----------------------------------------------------

    @RequestMapping(value = "",
                    method = RequestMethod.PUT)
    public ResponseEntity<Video> updateVideo(
            @RequestBody
            Video video) {
        if (videoService.findById(video.getId()) == null) {
            //            System.out.println("Video with id " + video.getId() + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
        videoService.updateVideo(video);
        return new ResponseEntity<Video>(video, HttpStatus.OK);
    }

    //------------------- Delete a Video --------------------------------------------------------

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE)
    public ResponseEntity<Video> deleteVideo(
            @PathVariable("id")
            long id) {
        System.out.println("Fetching & Deleting Video with id " + id);

        Video Video = videoService.findById(id);
        if (Video == null) {
            System.out.println("Unable to delete. Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }

        videoService.deleteVideoById(id);
        return new ResponseEntity<Video>(HttpStatus.NO_CONTENT);
    }

    public void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "- - - - - - " + s);
    }

}
