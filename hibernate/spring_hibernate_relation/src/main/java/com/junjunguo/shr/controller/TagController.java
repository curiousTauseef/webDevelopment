package com.junjunguo.shr.controller;

import com.junjunguo.shr.model.Tag;
import com.junjunguo.shr.model.User;
import com.junjunguo.shr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@RestController
@RequestMapping(value = "/tag/")
public class TagController {

    @Autowired
     TagService tagService;

    @RequestMapping(value = {"/{label}/", "/{label}"},
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> getTagByLabel(
            @PathVariable("label")
            String label) {
        System.out.println("Fetching Tag with label " + label);
        Tag tag = tagService.findByLabel(label);
        if (tag == null) {
            System.out.println("Tag with label " + label + " not found");
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }


    @RequestMapping(value = {"/{id}/", "/{id}"},
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> getTagById(
            @PathVariable("id")
            int id) {
        System.out.println("Fetching Video with id " + id);
        Tag tag = tagService.findByTagId(id);
        if (tag == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = {"/list/", "/list"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> listAllTags() {
        List<Tag> tags = tagService.findAllTags();
        if (tags.isEmpty()) {
            return new ResponseEntity<List<Tag>>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }
}
