package com.junjunguo.shr.service.servicesImpl;

import com.junjunguo.shr.service.LocationServices;
import com.junjunguo.shr.service.model.Location;
import com.junjunguo.shr.service.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public class LocationServicesImpl implements LocationServices {

    public final String REST_SERVICE_URI = Constant.SERVER_URL + "/location/";

    @SuppressWarnings("unchecked")
    public List<Location> listAllLocations() {
        List<Location> locations    = null;
        RestTemplate   restTemplate = new RestTemplate();
        try {
            locations = restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no Locations found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return locations;
    }

    public Location getById(int id) {
        Location     location     = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            location = restTemplate.getForObject(REST_SERVICE_URI + "/id/" + id, Location.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("location: {" + location + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return location;
    }
}
