package com.junjunguo.shr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Entity
@Table(name = "TAG")
public class Tag implements Serializable {
    /**
     * tag id
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "LABEL",
            unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String      label;
    @JsonIgnore
//    @JsonBackReference
    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Video.class)
    private List<Video> videos;

    public Tag(String label) {
        this.label = label;
    }

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag() {
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
     * Gets label.
     *
     * @return Value of label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets new label.
     *
     * @param label New value of label.
     */
    public void setLabel(String label) {
        this.label = label;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != tag.getId()) return false;
        if (!getLabel().equals(tag.getLabel())) return false;
        return !(getVideos() != null ? !getVideos().equals(tag.getVideos()) : tag.getVideos() != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLabel().hashCode();
        result = 31 * result + (getVideos() != null ? getVideos().hashCode() : 0);
        return result;
    }
}