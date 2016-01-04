package com.junjunguo.sae.controller;

import com.junjunguo.sae.model.Location;
import com.junjunguo.sae.service.LocationService;
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
@RequestMapping(value = "/location/")
public class LocationController {
    @Autowired LocationService locationService;

    @RequestMapping(value = {"/list/", "/list"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Location>> listAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        if (locations.isEmpty()) {
            return new ResponseEntity<List<Location>>(
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }

    @RequestMapping(value = {"/id/{id}/", "/id/{id}"},
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> getLocationById(
            @PathVariable("id")
            int id) {
        System.out.println("Fetching Video with id " + id);
        Location location = locationService.findById(id);
        if (location == null) {
            System.out.println("Video with id " + id + " not found");
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }
}
