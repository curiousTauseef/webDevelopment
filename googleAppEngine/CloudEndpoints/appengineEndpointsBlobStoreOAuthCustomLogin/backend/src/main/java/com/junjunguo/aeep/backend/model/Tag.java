package com.junjunguo.aeep.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
@Entity
@Index
public class Tag {
    @Ignore
    private List<Event> events;
    @Id
    private String label;


    /**
     * Instantiates a new Tag.
     */
    public Tag() {
        this.events = new ArrayList<>();
    }

    /**
     * Instantiates a new Tag.
     * @param label the label
     */
    public Tag(String label) {
        this();
        this.label = label;
    }


    /**
     * Gets events.
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Remove event.
     * @param event the event
     */
    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    /**
     * Add event.
     * @param event the event
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Sets events. will set at server
     * @param events the events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Gets label.
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}