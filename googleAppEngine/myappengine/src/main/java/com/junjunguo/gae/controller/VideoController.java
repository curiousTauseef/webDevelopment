package com.junjunguo.gae.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junjunguo.gae.model.Location;
import com.junjunguo.gae.model.Video;
import com.junjunguo.gae.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    //-------------------download a Video file------------------------------------------------------
    //    @RequestMapping(value = "/files/{video_id}",
    //                    method = RequestMethod.GET)
    //    @ResponseBody
    //    public FileSystemResource getFile(
    //            @PathVariable("video_id")
    //            long videoId) {
    //        return new FileSystemResource(videoService.getFileFor(videoId));
    //    }

    @RequestMapping(value = "/download/{video_id}",
                    method = RequestMethod.GET)
    public void download(HttpServletResponse response,
            @PathVariable("video_id")
            long videoId) throws IOException {

        File        file = new File(videoService.getFileFor(videoId));
        InputStream is   = new FileInputStream(file);

        // MIME type of the file
        response.setContentType("application/octet-stream");
        // Response header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        // Read from the file and write into the response
        OutputStream os     = response.getOutputStream();
        byte[]       buffer = new byte[1024];
        int          len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }

    //-------------------Create a Video------------------------------------------------------
    // add a video to the system
    @RequestMapping(value = "addVideo",
                    method = RequestMethod.POST)
    public ResponseEntity<Void> createVideo(
            @RequestBody
            Video video, UriComponentsBuilder ucBuilder) {
        if (videoService.isVideoExist(video.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //        videoService.addVideo(video);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/video/{id}/").buildAndExpand(video.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //    @RequestMapping(value = "",
    //                    method = RequestMethod.POST)
    //    public ResponseEntity<Void> createUser(
    //            @RequestBody
    //            User user

    // small file uploading without MultipartFile a video and string file
    @RequestMapping(value = "/upload_as_string",
                    method = RequestMethod.POST)
    public String uploadVideo(
            @RequestParam(value = "video",
                          required = false)
            String video,
            @RequestParam(value = "file",
                          required = false)
            String file) {
        log("video: \n" + video);
        Video vo;
        try {
            vo = jsonToVideo(video);
            log("vo to string : " + vo.toString());
            if (!file.isEmpty()) {
                log("file is not empty ;) " + file.substring(0, 10));
                //            log("v to string " + vo.toString());
                //                log(videoService.addVideo(vo, file));
                return "Succeed";
            } else {
                return "You failed to upload " + "" + " because the file was empty.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // file upload with MultipartFile and a video
    @RequestMapping(value = "/upload",
                    method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(
            @RequestParam("video")
            String video,
            @RequestParam("file")
            MultipartFile file) {
        log("e . up load called");
        if (!file.isEmpty()) {
            try {
                log("file size: " + file.getSize());
                videoService.addVideo(jsonToVideo(video), file);
                return "You successfully uploaded " + "!";
            } catch (Exception e) {
                return "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
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

    private Video jsonToVideo(String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(s, Video.class);
    }

    public void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "- - - - - - " + s);
    }

}
