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
    //    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    //    @Index
    //    private List<Ref<Tag>> tags;
    //    private List<Tag>
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

    public Event() {
        //        this.tags = new HashSet<>();
        this.tags = new ArrayList<>();
    }

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

    public List<Tag> getTags() {
        return tags;
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    //    public Key<Event> getKey() {
    //        return Key.create(Event.class, this.getId());
    //    }

    //    public void addTagsKey(Tag tag) {
    //        tagsKey.add()
    //    }
    //
    //    public void addTag(Tag tag) {
    //        this.tags.add(Ref.create(tag));
    //    }
    //
    //    public void removeTag(Tag tag) {
    //        this.tags.remove(Ref.create(tag));
    //    }
    //
    //    public List<Tag> getTags() {
    //        List<Tag> ts = new ArrayList<>();
    //        for (Ref<Tag> tagRef : this.tags) {
    //            ts.add(tagRef.get());
    //        }
    //        return ts;
    //    }
    //
    //    public void setTags(List<Tag> tags) {
    //        List<Ref<Tag>> tagRef = new ArrayList<>();
    //        for (Tag t : tags) {
    //            tagRef.add(Ref.create(t));
    //        }
    //        this.tags = tagRef;
    //    }

    public GeoPt getLocation() {
        return location;
    }

    public void setLocation(GeoPt location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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