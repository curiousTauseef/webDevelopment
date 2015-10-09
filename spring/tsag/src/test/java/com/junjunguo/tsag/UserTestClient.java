package com.junjunguo.tsag;

import com.junjunguo.tsag.model.User;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class UserTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/user/";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate
                .getForObject(REST_SERVICE_URI + "/list/", List.class);

        if (usersMap != null) {
            for (LinkedHashMap<String, Object> map : usersMap) {
                System.out.println(
                        "User :  Name=" + map.get("name") + ", Age=" +
                        map.get("age") + ", Salary=" + map.get("salary"));
                ;
            }
        } else {
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    private static void getUser() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "1", User.class);
        System.out.println(user);
    }

    /* GET */
    private static void getUserByEmail() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "c@china.cn", User.class);
        System.out.println("get by email: " + user);
    }

    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0, "Sarah", 51, 134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "", user, User.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(1, "Tomy", 33, 70000);
        restTemplate.put(REST_SERVICE_URI + "1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "3");
    }

    public static void main(String args[]) {
        listAllUsers();
        getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        listAllUsers();
    }
}