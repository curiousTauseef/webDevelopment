package com.junjunguo.shr;

import com.junjunguo.shr.service.servicesImpl.TagServicesImpl;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 26/10/15.
 */
public class ClientTesting {
    public static void main(String[] args) {
        //        System.out.println(new LocationServicesImpl().);
        log(new TagServicesImpl().createTag("CS"));

        //        User u = new User("Lee", "lee@gmail.com", "lee's password");
        //        System.out.println(new UserServicesImpl().createUser(u));
        //        List<Tag> ts = new ArrayList<Tag>();
        //        ts.add(new Tag("CS"));
        //        ts.add(new Tag("JPA"));
        //        Video v = new Video("title", ts, "new", "path", "name", "mp4", u, new Location(60.10, 10.3));
        //
        //        System.out.println(new VideoServicesImpl().createVideo(v));
//        log(new UserServicesImpl().createUser(new User("Matilde", "ola@tsag.com",
//                "matilde's password")));
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
    }

    public static void log(String s) {
        System.out.println(s);
    }
}
