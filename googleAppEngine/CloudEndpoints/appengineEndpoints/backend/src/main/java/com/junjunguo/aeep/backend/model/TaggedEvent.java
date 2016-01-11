package com.junjunguo.aeep.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 11, 2016.
 */
@Entity
public class TaggedEvent {
    @Index
    private long eventId;
    @Index
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
}
