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

    //-------------------Create a Video------------------------------------------------------

    @RequestMapping(value = "",
                    method = RequestMethod.POST)
    public ResponseEntity<Void> createVideo(
            @RequestBody
            Video video, UriComponentsBuilder ucBuilder) {
        //        TODO: how to indicate a video is already exist ?
        //        if (videoService.isVideoExist(video.getId())) {
        //            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        //        }
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
}