package com.junjunguo.springmvc;

/**
 * Created by GuoJunjun <junjunguo.com> on 23/10/15.
 */

import com.junjunguo.springmvc.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientTest {

    public static final String REST_SERVICE_URI = "http://localhost:8080" + "/new/";


    /* POST */
    private static void createUser(User user) {
        log("create new user API --------");
        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "create/", user, User.class);
            log("Location : " + uri.toASCIIString() + "/");
        } catch (org.springframework.web.client.RestClientException e) {
            if (e.getMessage().contains(HttpStatus.CONFLICT.toString())) {
            } else {
                log("oops! error occurred! " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        User u = new User();
        u.setEmail("ola@gmail.com");
        u.setFirstName("Ola");
        u.setLastName("Kari");
        u.setSsoId("11");
        u.setPassword("1111"); u.setId(1);
        createUser(u);

        log("user : " + u.toString());

    }


    public static Date getDate(String dateInString) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(dateInString);
            //            log(date.toString());
            //            log(sdf.format(date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void log(String s) {
        System.out.println("---------- log: " + s);
    }
}