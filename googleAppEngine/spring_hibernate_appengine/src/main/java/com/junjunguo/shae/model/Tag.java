package com.junjunguo.shae.model;

import javax.persistence.*;
import java.io.Serializable;

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
    private long id;

    @Column(name = "LABEL",
            unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String label;

    public Tag(String label) {
        this.label = label;
    }

    public Tag(long id, String label) {
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
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets tag id.
     *
     * @return Value of tag id.
     */
    public long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (getId() != tag.getId()) return false;
        return getLabel().equals(tag.getLabel());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getLabel().hashCode();
        return result;
    }
}