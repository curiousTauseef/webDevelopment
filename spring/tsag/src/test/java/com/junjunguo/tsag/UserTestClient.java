package com.junjunguo.tsag;

import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.User;
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
        log("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        try {
            List<LinkedHashMap<String, Object>> usersMap = restTemplate
                    .getForObject(REST_SERVICE_URI + "list/", List.class);

            if (usersMap != null) {
                for (LinkedHashMap<String, Object> map : usersMap) {
                    log("User {'Name':'" + map.get("name") +
                        "', 'email':'" + map.get("email") +
                        "', 'country':'" + map.get("country") +
                        "', 'tags':'" + map.get("tags") +
                        "', 'password':'" + map.get("password") +
                        "', 'registeredtime':'" + map.get("registeredtime") +
                        "', 'birth':'" + map.get("birth") + "'}");
                }
            } else {
                log("No user exist----------");
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllTags() {
        log("Testing list all tags API-----------");

        RestTemplate restTemplate = new RestTemplate();
        try {
            List<LinkedHashMap<String, Object>> tagsMap = restTemplate
                    .getForObject(REST_SERVICE_URI + "/tag/list/", List.class);

            if (tagsMap != null) {
                for (LinkedHashMap<String, Object> map : tagsMap) {
                    log("Tag :  Label=" + map.get("label") +
                        ", id=" + map.get("id") +
                        ", users=" + map.get("user")
                       );
                }
            } else {
                log("No user exist----------");
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* GET */
    private static void getUserByName(String name) {
        log("Testing getUserByName by name API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/name/" + name + "/", User.class);
            log("find user by name : " + user);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("user name: {" + name + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }

    }

    /* GET */
    private static void getUserByEmail(String email) {
        log("Testing getUser By Email API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            User user = restTemplate.getForObject(REST_SERVICE_URI + "/email/" + email + "/", User.class);
            if (user != null) {
                log("get by email: " + user);
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("user with email: {" + email + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* GET */
    private static void getTagById(int id) {
        log("Testing get tag By Tag id API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            Tag tag = restTemplate.getForObject(REST_SERVICE_URI + "/tag/id/" + id + "/", Tag.class);
            if (tag != null) {
                log("get by tag: " + tag);
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("tag with tag: {" + id + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* GET */
    private static void getTagByLabel(String label) {
        log("Testing get tag By Tag label API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            Tag tag = restTemplate.getForObject(REST_SERVICE_URI + "/tag/label/" + label + "/", Tag.class);
            if (tag != null) {
                log("get by tag: " + label);
            }
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("tag with tag: {" + label + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* POST */
    private static void createUser(User user) {
        log("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "create/", user, User.class);
            log("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                log("user: {" + user.toString() + "} already exist !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* POST */
    private static void createTag(String tag) {
        log("Testing create tag API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "tag/create/", tag);
            log("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
                log("user: {" + tag + "} already exist !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* PUT */
    private static void updateUser(User user) {
        log("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(REST_SERVICE_URI, user);
            //            restTemplate.put(REST_SERVICE_URI + user.getEmail() + "/", user);
            log("update user: " + user);
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("user: {" + user + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    /* DELETE */
    private static void deleteUserByEmail(String email) {
        log("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(REST_SERVICE_URI + email + "/");
            log("user with email: " + email + " deleted");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())) {
                log("user with email: {" + email + "} not found !");
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        //        createUser(new User("Ola", "ola@a.a", "ola's password"));
        User u = new User("Johan", "johan@gmail.com", "johan's password");
        u.addTagLabel("new");
        createTag("lucky");
        //        u.addTagLabel("lucky");
        List<Tag> ts = u.getTags();
        ts.add(new Tag("hei"));
        u.setTags(ts);
        createUser(u);
        //        createTag("NTNU");
        //        createTag("CS");
        //        getTagById(5);
        //        getTagByLabel("fun");
        //        getTagByLabel("not exit");
        listAllTags();
        listAllUsers();
        //        getUserByName("junjun");//by name

        //        getUserByEmail("ola@a.a");
        //        getUserByEmail("jonas@gmail.com");
        //        createUser(new User("Sarah", "sarah@a.a", "sarah's password"));
        //        listAllUsers();
        //        updateUser(new User("Sarah", "sarah@a.a", "Norway", "sarah's password updated",
        //                getDate("03 14 " + "16:02:37 2011")));
        //        listAllUsers();
                User jj = new User("JJ", "jj@gmail.com", "Norway", "jj's password updated",
                        getDate("03 14 " + "16:02:37 2011"));
                createUser(jj);
                listAllUsers();
                listAllTags();
                jj.addTagLabel("mac");
                jj.addTagLabel("win");
                updateUser(jj);
                listAllTags();
                listAllUsers();
        //        createUser(new User("Jonas", "jonas@gmail.co", "jo's password"));
        //        deleteUserByEmail("sarah@a.a");
        //        listAllUsers();
    }


    public static Date getDate(String dateInString) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(dateInString);
            log(date.toString());
            log(sdf.format(date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void log(String s) {
        System.out.println("---------- User Test Client: " + s);
    }
}