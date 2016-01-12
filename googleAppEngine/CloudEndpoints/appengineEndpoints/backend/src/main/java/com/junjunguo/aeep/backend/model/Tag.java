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
    //    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    //    @Index
    //    private List<Ref<Event>> events;
    //    private List<Long> eventKeys;
    @Ignore
    private List<Event> events;
    @Id
    private String label;


    public Tag() {
        this.events = new ArrayList<>();
    }

    public Tag(String label) {
        this();
        this.label = label;
    }


    public List<Event> getEvents() {
        return events;
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    //    public void addEvent(Event event) {
    //        this.events.add(Ref.create(event));
    //    }
    //
    //    public void removeEvent(Event event) {
    //        this.events.remove(Ref.create(event));
    //    }
    //
    //    public List<Event> getEvents() {
    //        List<Event> el = new ArrayList<>();
    //        for (Ref<Event> e : events) {
    //            el.add(e.get());
    //        }
    //        return el;
    //    }
    //
    //    public void setEvents(List<Event> events) {
    //        List<Ref<Event>> lr = new ArrayList<>();
    //        for (Event e : events) {
    //            lr.add(Ref.create(e));
    //        }
    //        this.events = lr;
    //    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}