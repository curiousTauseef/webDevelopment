package com.junjunguo.sae.test;

import com.junjunguo.sae.test.client.model.Location;
import com.junjunguo.sae.test.client.model.Tag;
import com.junjunguo.sae.test.client.model.User;
import com.junjunguo.sae.test.client.services.LocaServices;
import com.junjunguo.sae.test.client.services.UserServices;
import com.junjunguo.sae.test.client.services.VideoServices;
import com.junjunguo.sae.test.client.services.servicesImpl.LocaServicesImpl;
import com.junjunguo.sae.test.client.services.servicesImpl.TagServicesImpl;
import com.junjunguo.sae.test.client.services.servicesImpl.UserServicesImpl;
import com.junjunguo.sae.test.client.services.servicesImpl.VideoServicesImpl;
import com.junjunguo.sae.test.client.model.Video;
import com.junjunguo.sae.test.client.services.TagServices;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 26/10/15.
 */
public class ClientTesting {
    public static void main(String[] args) {
        LocaServices  ls = new LocaServicesImpl();
        UserServices  us = new UserServicesImpl();
        VideoServices vs = new VideoServicesImpl();
        TagServices   ts = new TagServicesImpl();

        User jj = new User("JunjunGuo", "GuoJunjun@Gmail.com", "guojunjun's password");
        log(us.createUser(jj));

        User      lee  = new User("Lee", "lee@gmail.com", "lee's password");
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("read"));
        tags.add(new Tag("JPA"));
        String filePath = "/Users/Junjun/Movies/testing.f4v";
        String textPath = "/Users/Junjun/test.txt";
        String pdfPath =
                "/Users/Junjun/GoogleDrive/documentsOnGoogleDrive/Lesing/java-persistence-developer-guide.pdf";
        String imgPath = "/Users/Junjun/Dropbox/doc/img/git.png";
        //        Video  v       = new Video("text", tags, "text", textPath, "test", "txt", lee, new Location(60.10, 13.3));
        Video v = new Video("file", tags, "a file", imgPath, "v", "file", jj, new Location(61.10, 10.3));
        //                File   file     = new File(v.getFilePath());
        //                log(vs.uploadVideo(v));
        //        log(vs.fetchVideoFile(5));
        //                        User matilde = new User("Matilde", "ola@tsag.com", "matilde's password");
        //                        log(us.createUser(matilde));
        //                log(us.listAllUsers().toString());
        //                log(us.createUser(jj));
        //                log(us.listAllUsers().toString());
        //                jj.setGender(Gender.MALE);
        //                log(us.updateUser(jj));
        //                log(us.listAllUsers().toString());
        //                User eva = new User("Eva", "eva@yahoo.com", "eva's password");
        //                log(us.createUser(eva));
        //                log(us.listAllUsers().toString());
        //        log(us.deleteUserByEmail(eva.getEmail()));
        //        log(us.listAllUsers().toString());
        //        tags.add(new Tag("NTNU"));
        //        Video v2 = new Video("title: v2", tags, "new", "path", "name", "mp4", jj, new Location(60.11, 10.33));
        //        tags.add(new Tag("TSAG"));
        //        Video v3 = new Video("title: v2", tags, "new", "path", "name", "mp4", jj, new Location(62.10, 11.3));
        //        log(vs.createVideo(v2));
        //        log(vs.createVideo(v3));
        //        log(us.getUserByName("ol") + "");//by name
        //        log(us.getUserByEmail("matilde@a.a") + "");
        //        log(us.getUserByEmail("jonas@gmail.com") + "");
        //        log(us.createUser(new User("Philip", "philip@a.a", "philip's password")));
        //        log(us.updateUser(new User("Philip", "philip@a.a", "philip's password updated", "Norway", Gender.FEMALE,
        //                new MyDate().getDate("2000-02-02 16:02:37"))));
        //        log(us.createUser(new User("Stian", "stian@gmail.com", "stian's password")));
        //        log(us.createUser(new User("Kenzie", "kenzie@gmail.com", "kenzie's password")));
        //        log(us.createUser(new User("Val", "val@tsag.com", "val's password")));
        //        log(us.createUser(new User("Stine", "stine@hotmail.com", "stine's password")));
        //        log(us.listAllUsers().toString());
        //        log(us.deleteUserByEmail(lee.getEmail()));
        //        Location l = new Location(60.10, 10.3);
        //        log(vs.findNearBy(l, 1) + "");
    }

    public static void log(String s) {
        System.out.println(s);
    }
}
