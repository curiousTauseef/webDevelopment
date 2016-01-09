package com.junjunguo.aeep.backend.model;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
public class Event {
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private List<Key<Tag>> tags;
    private GeoPt location;
    @Id
    private long id;
    private String ownerEmail;
    private String title;
    private String description;
    private boolean hasVideo;
    private String videoPath;
    private Date uploadTime;

    public Event() {
        this.tags = new ArrayList<>();
    }

    public void addTag(Key<Tag> tagKey) {
        this.tags.add(tagKey);
    }

    public void removeTag(Key<Tag> tagKey) {
        this.tags.remove(tagKey);
    }

    public List<Key<Tag>> getTags() {
        return tags;
    }

    public void setTags(List<Key<Tag>> tags) {
        this.tags = tags;
    }

    public GeoPt getLocation() {
        return location;
    }

    public void setLocation(GeoPt location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}