package com.junjunguo.shae.controller;

import com.junjunguo.shae.model.Tag;
import com.junjunguo.shae.service.TagService;
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
@RequestMapping(value = "/tag/")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = {"/list/", "/list", "/"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> listAllTags() {
        List<Tag> tags = tagService.findAllTags();
        if (tags.isEmpty()) {
            return new ResponseEntity<List<Tag>>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }

    @RequestMapping(value = {"/label/{label}/", "/label/{label}"},
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


    @RequestMapping(value = {"/id/{id}/", "/id/{id}"},
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> getTagById(
            @PathVariable("id")
            int id) {
        System.out.println("Fetching tag with id " + id);
        Tag tag = tagService.findByTagId(id);
        if (tag == null) {
            System.out.println("tag with id " + id + " not found");
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST)
    public ResponseEntity<Void> createTag(
            @RequestBody
            String label, UriComponentsBuilder ucBuilder) {
        if (tagService.isLabelExist(label)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        tagService.addTag(label);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tag/label/{label}").buildAndExpand(label).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}
