package com.junjunguo.shr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Entity
@Table(name = "TAG")
public class Tag {
    /**
     * tag id
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int         id;
    @Column(name = "TITLE",
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String      title;
    //    @JsonManagedReference
    //    @JsonIgnore
    @ManyToMany(mappedBy = "tags",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL,
                targetEntity = Video.class)
    private List<Video> videos;

    public Tag(String title) {
        this.title = title;
    }

    /**
     * Sets new tag id.
     *
     * @param id New value of tag id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets tag id.
     *
     * @return Value of tag id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets videos.
     *
     * @return Value of videos.
     */
    public List<Video> getVideos() { return videos; }

    /**
     * Sets new videos.
     *
     * @param videos New value of videos.
     */
    public void setVideos(List<Video> videos) { this.videos = videos; }
}