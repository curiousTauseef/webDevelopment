package com.junjunguo.aeep.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 12, 2016.
 */
@Entity
public class UsersEvents {
    @Id
    private long eventId;
    @Index
    private String ownerEmail;

    /**
     * Instantiates a new Users events.
     */
    public UsersEvents() {
    }

    /**
     * Instantiates a new Users events.
     * @param eventId    the event id
     * @param ownerEmail the owner email
     */
    public UsersEvents(long eventId, String ownerEmail) {
        this.eventId = eventId;
        this.ownerEmail = ownerEmail;
    }

    /**
     * Gets event id.
     * @return the event id
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Sets event id.
     * @param eventId the event id
     */
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets owner email.
     * @return the owner email
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets owner email.
     * @param ownerEmail the owner email
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
