package com.junjunguo.tsag;

import com.junjunguo.tsag.testmodel.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/user/";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        try {
            List<LinkedHashMap<String, Object>> usersMap = restTemplate
                    .getForObject(REST_SERVICE_URI + "/list/", List.class);

            if (usersMap != null) {
                for (LinkedHashMap<String, Object> map : usersMap) {
                    System.out.println("User :  Name=" + map.get("name") +
                            ", email=" + map.get("email") +
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
    private static void getUserByName(String name) {
        System.out.println("Testing getUserByName by name API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/name/" + name, User.class);
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
    private static void getUserByEmail(String email) {
        System.out.println("Testing getUser By Email API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/email/" + email, User.class);
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
    private static void createUser(User user) {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI, user, User.class);
            System.out.println("Location : " + uri.toASCIIString());
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                System.out.println("user: {" + user.toString() + "} already exist !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* PUT */
    private static void updateUser(User user) {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI + "sarah@a.a", user);
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
    private static void deleteUserByEmail(String email) {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + email);
            System.out.println("user with email: " + email + "deleted");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                System.out.println("user with email: {" + email + "} not found !");
            } else {
                System.out.println("oops! error occurred! " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        createUser(new User("Ola", "ola@a.a", "ola's password"));
        listAllUsers();
        getUserByName("ol");//by name
        getUserByEmail("ola@a.a");
        createUser(new User("Sarah", "sarah@a.a", "sarah's password"));
        listAllUsers();
        updateUser(new User("Sarah", "sarah@a.a", "Norway", "sarah's password",
                getDate("03 14 " + "16:02:37 2011")));
        listAllUsers();
        deleteUserByEmail("sarah@a.a");
        listAllUsers();
        createUser(new User("Jonas", "jonas@gmail.co", "jo's password"));
        listAllUsers();
    }


    public static Date getDate(String dateInString) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(dateInString);
            System.out.println(date);
            System.out.println(sdf.format(date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}