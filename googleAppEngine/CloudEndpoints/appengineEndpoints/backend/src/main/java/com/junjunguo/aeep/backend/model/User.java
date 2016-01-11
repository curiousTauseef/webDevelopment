package com.junjunguo.aeep.backend.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import java.util.ArrayList;
import java.util.List;

/**
 * UserAccount entity.
 * <p>
 * This file is part of appengineEndpoints
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
@Entity
public class User {
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    @Index
    private List<Ref<Event>> events;
    private String firstName;
    private String lastName;
    @Id
    private String email;
    private Gender gender;
    @Unindex
    private String password;

    public User() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        this.events.add(Ref.create(event));
    }

    public void removeEvent(Event event) {
        this.events.remove(Ref.create(event));
    }

    public List<Event> getEvents() {
        List<Event> el = new ArrayList<>();
        for (Ref<Event> e : events) {
            el.add(e.get());
        }
        return el;
    }

//    public void setEvents(List<Key<Event>> events) {
//        this.events = events;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
