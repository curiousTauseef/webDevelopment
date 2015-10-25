package com.junjunguo.shr.controller;

import com.junjunguo.shr.model.Video;
import com.junjunguo.shr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @RequestMapping(value = "/list/",
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

    @RequestMapping(value = "/id/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Video> getVideoById(
            @PathVariable("id")
            int id) {
        System.out.println("Fetching Video with id " + id);
        Video Video = videoService.findById(id);
        if (Video == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Video>(Video, HttpStatus.OK);
    }

    @RequestMapping(value = "/title/{title}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideoByTitle(
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

    @RequestMapping(value = "/tags/{tags}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideoByTags(
            @PathVariable("tags")
            List<String> tags) {
        System.out.println("Fetching Video with tags " + tags);
        List<Video> videos = videoService.findByTags(tags);
        if (videos == null || videos.isEmpty()) {
            System.out.println("Video with tags " + tags + " not found");
            return new ResponseEntity<List<Video>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }

    @RequestMapping(value = "/email/{email:.+}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideoByEmail(
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

    //-------------------Create a Video------------------------------------------------------

        @RequestMapping(value = "", method = RequestMethod.POST)
        public ResponseEntity<Void> createVideo(
                @RequestBody
                Video video, UriComponentsBuilder ucBuilder) {
            System.out.println("Creating Video " + video.getId());

            if (videoService.hasVideo(video.getId())) {
                System.out.println("A Video with id " + video.getId() + " already exist");
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);
            }

            videoService.addVideo(video);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("video/id/{id}").buildAndExpand(video.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }


    //------------------- Update a Video -----------------------------------------------------

    @RequestMapping(value = "",
                    method = RequestMethod.PUT)
    public ResponseEntity<Video> updateVideo(
            @RequestBody
            Video video) {
        System.out.println("Updating Video " + video);

        Video currentVideo = videoService.findById(video.getId());

        if (currentVideo == null) {
            System.out.println("Video with id " + video.getId() + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }

        //        currentVideo.updateVideo(Video);

        videoService.updateVideo(currentVideo);
        return new ResponseEntity<Video>(currentVideo, HttpStatus.OK);
    }

    //------------------- Delete a Video --------------------------------------------------------

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE)
    public ResponseEntity<Video> deleteVideo(
            @PathVariable("id")
            int id) {
        System.out.println("Fetching & Deleting Video with id " + id);

        Video Video = videoService.findById(id);
        if (Video == null) {
            System.out.println("Unable to delete. Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }

        videoService.deleteVideoById(id);
        return new ResponseEntity<Video>(HttpStatus.NO_CONTENT);
    }
}
