package com.junjunguo.tsag;

import com.junjunguo.tsag.model.User;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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
                System.out.println("User :  Name=" + map.get("name") +
                                   ", email=" + map.get("email") +
                                   ", password=" + map.get("password") +
                                   ", registered time=" + map.get("registeredtime") +
                                   ", birth=" + map.get("birth"));
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
        User user = restTemplate.getForObject(REST_SERVICE_URI + "/name/Ola", User.class);
        System.out.println(user);
    }

    /* GET */
    private static void getUserByEmail() {
        System.out.println("Testing getUserByEmail API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "/email/ola@a.a", User.class);
        System.out.println("get by email: " + user);
    }

    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("Sarah", "sarah@a.a", "sarah's password");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI, user, User.class);
        System.out.println("Location : " + uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("Sarah", "sarah@a.a", "Norway", "sarah's password",
                             getCalendar("03 14 " + "16:02:37 2011").getTimeInMillis());
        restTemplate.put(REST_SERVICE_URI + "sarah@a.a", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "sarah@a.a");
        System.out.println("deleted: sarah@a.a");
    }

    public static void main(String args[]) {
        listAllUsers();
        getUser();//by name
        getUserByEmail();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        listAllUsers();
    }

    public static Calendar getCalendar(String dateTime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            cal.setTime(sdf.parse(dateTime));
            //            cal.setTime(sdf.parse("03 14 16:02:37 2011"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static Calendar getCalendar(String month, String day, String time, String year) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            cal.setTime(sdf.parse(month + " " + day + " " + time + " " + year));
            //            cal.setTime(sdf.parse("03 14 16:02:37 2011"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}