package com.junjunguo.shr.client.services.servicesImpl;

import com.junjunguo.shr.client.services.TagServices;
import com.junjunguo.shr.client.util.Constant;
import com.junjunguo.shr.client.model.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public class TagServicesImpl implements TagServices {
    public final String REST_SERVICE_URI = Constant.SERVER_URL + "/tag/";

    @SuppressWarnings("unchecked")
    public List<Tag> listAllTags() {
        List<Tag>    tags         = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            tags = restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("no Tags found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return tags;
    }

    public Tag getByTagId(long id) {
        Tag          tag          = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            tag = restTemplate.getForObject(REST_SERVICE_URI + "/id/" + id, Tag.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("tag: {" + tag + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return tag;
    }

    public Tag getByTagLabel(String label) {
        Tag          tag          = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            tag = restTemplate.getForObject(REST_SERVICE_URI + "/label/" + label, Tag.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("tag with label: {" + label + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return tag;
    }

    public String createTag(String label) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, label);
            message = "create tag: {" + label + "} succeed";
            System.out.println("uri : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "tag: {" + label + "} already exist !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }


}
