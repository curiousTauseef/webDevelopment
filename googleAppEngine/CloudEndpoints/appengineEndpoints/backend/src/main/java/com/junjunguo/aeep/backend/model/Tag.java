package com.junjunguo.aeep.backend.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
@Entity
public class Tag {
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private List<Key<Event>> events;
//    private Long id;
    @Id
    private String label;

    public Tag() {
//        this.id = null;
        this.events = new ArrayList<>();
    }

    public Tag(String label) {
        this();
        this.label = label;
    }

    public void addEvent(Key<Event> eventKey) {
        this.events.add(eventKey);
    }

    public void removeEvent(Key<Event> eventKey) {
        this.events.remove(eventKey);
    }


    public List<Key<Event>> getEvents() {
        return events;
    }

    public void setEvents(List<Key<Event>> events) {
        this.events = events;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}