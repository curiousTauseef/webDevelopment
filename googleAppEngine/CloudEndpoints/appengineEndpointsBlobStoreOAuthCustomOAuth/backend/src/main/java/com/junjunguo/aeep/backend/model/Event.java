package com.junjunguo.aeep.backend.model;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 * <p/>
 * An Event start with a video may end without video;
 */
@Entity
@Index
public class Event {

    @Ignore
    private List<Tag> tags;
    private GeoPt location;
    @Id
    private Long id;
    private String ownerEmail;
    private String title;
    @Unindex
    private String description;
    private boolean hasVideo;
    @Unindex
    private String videoPath;
    private Date uploadTime;

    /**
     * Instantiates a new Event.
     */
    public Event() {
        this.tags = new ArrayList<>();
    }

    /**
     * Instantiates a new Event.
     * @param e the e
     */
    public Event(Event e) {
        this();
        this.tags = e.getTags();
        this.location = e.getLocation();
        this.ownerEmail = e.getOwnerEmail();
        this.title = e.getTitle();
        this.description = e.getDescription();
        this.hasVideo = e.hasVideo;
        this.videoPath = e.videoPath;
        this.uploadTime = e.uploadTime;
    }

    /**
     * Gets tags.
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Remove tag.
     * @param tag the tag
     */
    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    /**
     * Add tag.
     * @param tag the tag
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Sets tags.
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    /**
     * Gets location.
     * @return the location
     */
    public GeoPt getLocation() {
        return location;
    }

    /**
     * Sets location.
     * @param location the location
     */
    public void setLocation(GeoPt location) {
        this.location = location;
    }

    /**
     * Gets id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id. will be Generated by server !
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * Gets title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Is has video boolean.
     * @return the boolean
     */
    public boolean isHasVideo() {
        return hasVideo;
    }

    /**
     * Sets has video.
     * @param hasVideo the has video
     */
    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    /**
     * Gets video path.
     * @return the video path
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * Sets video path.
     * @param videoPath the video path
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * Gets upload time.
     * @return the upload time
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * Sets upload time.
     * @param uploadTime the upload time
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

}