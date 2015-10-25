package com.junjunguo.shr;

import com.junjunguo.shr.model.Tag;
import com.junjunguo.shr.model.User;
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
public class ClientUser {

    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/user/";

    /* GET */
    @SuppressWarnings("unchecked")
    public static void listAllUsers() {
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
    public static void getUserByName(String name) {
        System.out.println("Testing getUserByName by name API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/name/" + name + "/", User.class);
            System.out.println("find user by name : " + user);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user name: {" + name + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }

    }

    /* GET */
    public static void getUserByEmail(String email) {
        System.out.println("Testing getUser By Email API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/email/" + email + "/", User.class);
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
    }

    /* POST */
    public static void createUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, user, User.class);
            System.out.println("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                System.out.println("user: {" + user.toString() + "} already exist !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* PUT */
    public static void updateUser(User user) {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, user);
            //            restTemplate.put(REST_SERVICE_URI + user.getEmail() + "/", user);
            System.out.println("update user: " + user);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user: {" + user + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* DELETE */
    public static void deleteUserByEmail(String email) {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + email + "/");
            System.out.println("user with email: " + email + " deleted");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user with email: {" + email + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
    }

//    public static void main(String args[]) {


//        User u = new User("Lee", "lee@gmail.com", "lee's password");
//
//        List<Tag> ts = new ArrayList<Tag>();
//        ts.add(new Tag("CS"));
//        ts.add(new Tag("JPA"));

//        createUser(new User("Matilde", "ola@tsag.com", "matilde's password"));
//        createUser(new User("Eva", "eva@yahoo.com", "eva's password"));
//        listAllUsers();
//        getUserByName("ol");//by name
//        getUserByEmail("matilde@a.a");
//        getUserByEmail("jonas@gmail.com");
//        createUser(new User("Philip", "philip@a.a", "philip's password"));
//        listAllUsers();
//        updateUser(new User("Philip", "philip@a.a", "Norway", "philip's password updated",
//                new MyDate().getDate("2000-02-02 16:02:37")));
//        listAllUsers();
//        createUser(new User("Stian", "stian@gmail.com", "stian's password"));
//        createUser(new User("Kenzie", "kenzie@gmail.com", "kenzie's password"));
//        createUser(new User("Val", "val@tsag.com", "val's password"));
//        listAllUsers();
//        createUser(new User("Stine", "stine@hotmail.com", "stine's password"));
//        deleteUserByEmail("philip@a.a");
//        listAllUsers();
//    }
}
