package com.junjunguo.shr.controller;

import com.junjunguo.shr.model.Location;
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
import java.util.Map;

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
    public ResponseEntity<Void> createVideo(
            @RequestBody
            //            MultiValueMap<String, Object> map
                    Map<String, Object> map
            //                                Video video
            , UriComponentsBuilder ucBuilder) {
        log("create video");
        Video video = (Video) map.get("video");
        log("video: " + video);
        //                videoService.addVideo(video, (MultipartFile) map.get("file"));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("video/id/{id}").buildAndExpand(video.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/upload",
                    method = RequestMethod.POST)
    public String handleFileUpload(
            @RequestParam(value = "video",
                          required = false)
            String video,
            @RequestParam(value = "file",
                          required = false)
            String file) {
        log("video: " + video + " file: " + file.substring(0, 10));
        if (!file.isEmpty()) {
            //            try {
            //                byte[] bytes = file.getBytes();
            //                BufferedOutputStream stream =
            //                        new BufferedOutputStream(new FileOutputStream(new File(name)));
            //                stream.write(bytes);
            //                stream.close();
            //                return "You successfully uploaded " + name + "!";
            //            } catch (Exception e) {
            //                return "You failed to upload " + name + " => " + e.getMessage();
            //            }
            //            return videoService.addVideo();
            return "upload file";
        } else {
            return "You failed to upload " + "" + " because the file was empty.";
        }
    }

    //    @RequestMapping(value = "/upload",
    //                    method = RequestMethod.POST)
    //    public String save(
    //            @RequestParam("file")
    //            MultipartFile file) {
    //
    //        log("fine name : " + file.getName());
    //        // Save it to i.e. database
    //        // dao.save(file);
    //        return "fileUpload";
    //    }


    //    @RequestMapping(value = "",
    //                    method = RequestMethod.POST)
    //    public ResponseEntity<Void> createVideo(
    //            @RequestBody
    //            //            Video video, UriComponentsBuilder ucBuilder) {
    //
    //                    //            @RequestParam("video")
    //                    //            Video video,
    //                    //            @RequestParam("file")
    //                    //            File file) {
    //                    MultiValueMap<String, Object> map, UriComponentsBuilder ucBuilder) {
    //        //        TODO: how to indicate a video is already exist ?
    //        //        if (videoService.isVideoExist(video.getId())) {
    //        //            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    //        //        }
    //
    //        log("create video");
    //        Video video = (Video) map.get("video");
    //        log("video: " + video);
    //        videoService.addVideo(video, (MultipartFile) map.get("file"));
    //        //        videoService.addVideo(video, null);
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.setLocation(ucBuilder.path("video/id/{id}").buildAndExpand(video.getId()).toUri());
    //        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    //    }


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
