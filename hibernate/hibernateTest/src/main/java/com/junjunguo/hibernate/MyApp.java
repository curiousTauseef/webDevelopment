package com.junjunguo.hibernate;

import com.junjunguo.hibernate.model.Tag;
import com.junjunguo.hibernate.model.User;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by GuoJunjun <junjunguo.com> on 20/10/15.
 */

public class MyApp {
    public static void main(String[] args) {

        System.out.println("Hibernate many to many (Annotation)");
        Session session = HibernateConfig.getInstance().getSessionFactory().openSession();

        session.beginTransaction();

        User user = new User("Hibernate", "hibernate@gmail.com", "hibernate", "hi's password updated",
                getDate("03 14 16:02:37 2015"));

        Tag tag = new Tag();
        tag.setLabel("Hibernate");

        Tag tag2 = new Tag();
        tag2.setLabel("hibernation");

        List<Tag> tags = new ArrayList<Tag>();
        tags.add(tag);
        tags.add(tag2);

        user.setTags(tags);

        session.save(user);

        session.getTransaction().commit();
        System.out.println("Done");
    }

    public static Date getDate(String dateInString) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm dd HH:mm:ss yyyy", Locale.getDefault());
        try {
            Date date = sdf.parse(dateInString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
