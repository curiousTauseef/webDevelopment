package com.junjunguo.shr.service.servicesImpl;

import com.junjunguo.shr.service.UserServices;
import com.junjunguo.shr.service.model.User;
import com.junjunguo.shr.service.util.Constant;
import com.junjunguo.shr.util.MyDate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    public void listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        try {
            List<LinkedHashMap<String, Object>> usersMap = restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);

            if (usersMap != null) {
                for (LinkedHashMap<String, Object> map : usersMap) {
                    System.out.println("User :  Name=" + map.get("name") +
                                       ", email=" + map.get("email") +
                                       ", country=" + map.get("country") +
                                       ", password=" + map.get("password") +
                                       ", registered time=" + map.get("registeredtime") +
                                       ", birth=" + map.get("birth"));
                }
            } else {
                System.out.println("No user exist----------");
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
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
        String       message      = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, user, User.class);
            message = "succeed";
            //            System.out.println("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                message = "user: {" + user.toString() + "} already exist !";
                //                System.out.println("user: {" + user.toString() + "} already exist !");
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* PUT */
    public String updateUser(User user) {
        //        System.out.println("Testing update User API----------");
        String       message      = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, user);
            //            restTemplate.put(REST_SERVICE_URI + user.getEmail() + "/", user);
            //            System.out.println("update user: " + user);
            message = "succeed";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                //                System.out.println("user: {" + user + "} not found !");
                message = "user: {" + user + "} not found !";
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "oops! error occurred! " + e.getMessage();
            }
        }
        return message;
    }

    /* DELETE */
    public String deleteUserByEmail(String email) {
        //        System.out.println("Testing delete User API----------");
        String       message      = "";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + email + "/");
            //            System.out.println("user with email: " + email + " deleted");
            message = "succeed";
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                //                System.out.println("user with email: {" + email + "} not found !");
                message = "user with email: {" + email + "} not found !";
            } else {
                //                System.out.println("oops! error occurred! " + e.getMessage());
                message = "\"oops! error occurred! \" + e.getMessage()";
            }
        }
        return message;
    }

}
