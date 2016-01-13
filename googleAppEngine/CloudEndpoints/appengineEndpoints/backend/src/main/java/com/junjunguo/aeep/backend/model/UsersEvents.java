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

    public UsersEvents() {
    }

    public UsersEvents(long eventId, String ownerEmail) {
        this.eventId = eventId;
        this.ownerEmail = ownerEmail;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
