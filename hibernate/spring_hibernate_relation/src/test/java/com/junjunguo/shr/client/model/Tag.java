package com.junjunguo.shr.client.model;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public class Tag {
    /**
     * tag id
     */
    private long   id;
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