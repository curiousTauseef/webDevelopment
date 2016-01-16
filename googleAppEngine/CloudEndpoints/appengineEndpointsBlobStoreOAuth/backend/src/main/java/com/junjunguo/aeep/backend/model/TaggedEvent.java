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

    /**
     * Instantiates a new Tagged event.
     */
    public TaggedEvent() {
    }

    /**
     * Instantiates a new Tagged event.
     * @param eventId the event id
     * @param tagId   the tag id
     */
    public TaggedEvent(long eventId, String tagId) {
        this.eventId = eventId;
        this.tagId = tagId;
    }

    /**
     * Gets event id.
     * @return the event id
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Sets event id.
     * @param eventId the event id
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets tag id.
     * @return the tag id
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * Sets tag id.
     * @param tagId the tag id
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * Gets id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets event id.
     * @param eventId the event id
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
