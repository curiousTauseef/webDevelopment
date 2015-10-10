package com.junjunguo.tsag.controller;

import com.junjunguo.tsag.model.Video;
import com.junjunguo.tsag.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/video/")
public class VideoController {

    @Autowired VideoService videoService;
    //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<Video>> listAllVideos() {
        List<Video> Videos = videoService.findAllVideos();
        if (Videos.isEmpty()) {
            return new ResponseEntity<List<Video>>(
                    HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Video>>(Videos, HttpStatus.OK);
    }


    //    -------------------Retrieve Video----------------------------------------------------

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Video> getVideo(
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

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> getVideoByName(
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

    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET,
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
            Video Video, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Video " + Video.getId());

        if (videoService.isVideoExist(Video.getId())) {
            System.out.println("A Video with id " + Video.getId() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        videoService.addVideo(Video);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/id/{id}").buildAndExpand(Video.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //------------------- Update a Video -----------------------------------------------------

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Video> updateVideo(
            @PathVariable("id")
            int id,
            @RequestBody
            Video Video) {
        System.out.println("Updating Video " + id);

        Video currentVideo = videoService.findById(id);

        if (currentVideo == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Video>(HttpStatus.NOT_FOUND);
        }

        currentVideo.updateVideo(Video);

        videoService.updateVideo(currentVideo);
        return new ResponseEntity<Video>(currentVideo, HttpStatus.OK);
    }

    //------------------- Delete a Video --------------------------------------------------------

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
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
