package com.junjunguo.aeep.backend.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of appengineEndpoints
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
@Entity
public class Tag {
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    @Index
    private List<Key<Event>> events;
    @Id
    private String label;

    public Tag() {
        this.events = new ArrayList<>();
    }

    public Tag(String label) {
        this();
        this.label = label;
    }

    public void addEvent(Event event) {
        this.events.add(Key.create(Event.class, event.getId()));
    }

    public void removeEvent(Event event) {
        this.events.remove(Key.create(Event.class, event.getId()));
    }

    public List<Key<Event>> getEvents() {
        return events;
    }

    public void setEvents(List<Key<Event>> events) {
        this.events = events;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}