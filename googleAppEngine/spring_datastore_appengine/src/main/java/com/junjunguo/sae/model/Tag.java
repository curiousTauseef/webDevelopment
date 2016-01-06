package com.junjunguo.sae.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * This file is part of spring_datastore_appengine.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 04/01/16.
 */
@Entity
public class Tag {
    /**
     * tag id
     */
    @Id
    private long id;

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
}
