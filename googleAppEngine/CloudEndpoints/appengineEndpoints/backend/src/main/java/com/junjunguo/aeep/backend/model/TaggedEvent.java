package com.junjunguo.aeep.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 11, 2016.
 */
@Entity
@Index
public class TaggedEvent {
    @Id
    private Long id;
    private long eventId;
    private String tagId;

    public TaggedEvent() {
    }

    public TaggedEvent(long eventId, String tagId) {
        this.eventId = eventId;
        this.tagId = tagId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
