package com.junjunguo.shae.client.services.servicesImpl;

import com.junjunguo.shae.client.util.Constant;
import com.junjunguo.shae.client.model.User;
import com.junjunguo.shae.client.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public class UserServicesImpl implements UserServices {

    public final String REST_SERVICE_URI = Constant.SERVER_URL + "/user/";

    /* GET */
    @SuppressWarnings("unchecked")
    public List<User> listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
            return null;
        }
    }

    /* GET */
    public User getUserByName(String name) {
        User         user         = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            user = restTemplate.getForObject(REST_SERVICE_URI + "/name/" + name + "/", User.class);
            System.out.println("find user by name : " + user);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user name: {" + name + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return user;
    }

    /* GET */
    public User getUserByEmail(String email) {
        User         user         = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            user = restTemplate.getForObject(REST_SERVICE_URI + "/email/" + email + "/", User.class);
            if (user != null) {
                System.out.println("get by email: " + user);
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user with email: {" + email + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
        return user;
    }

    /* POST */
    public String createUser(User user) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, user, User.class);
            message = "create user: " + user + " succeed";
            System.out.println("Location : " + uri.toASCIIString());
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "user: {" + user.toString() + "} already exist !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* PUT */
    public String updateUser(User user) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, user);
            message = "update user: " + user + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                message = "user: {" + user + "} not found !";
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* DELETE */
    public String deleteUserByEmail(String email) {
        String       message;
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + email + "/");
            message = "delete user with email: " + email + " !succeed!";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                message = "user with email: {" + email + "} not found !";
            } else if (e.getMessage().contains(HttpStatus.NOT_ACCEPTABLE.toString())) {
                message = "Need to delete all owned videos to complete this action! " + e.getMessage();
            } else {
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }
}
