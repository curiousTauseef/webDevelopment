package com.junjunguo.one2many.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of one2many.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */

@Entity
@Table(name = "TAG")
public class Tag {
    /**
     * TAG ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private int    id;
    @Column(name = "LABEL",
            nullable = false,
            columnDefinition = "VARCHAR(255)",
            unique = true)
    private String label;

    @ManyToOne()
    private User user;


    public Tag(String label) {
        this.label = label;
    }

    public Tag() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets TAG ID.
     *
     * @return Value of TAG ID.
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
     * Sets new TAG ID.
     *
     * @param id New value of TAG ID.
     */
    void setId(int id) {
        this.id = id;
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
    public String toString() {
        return "Tag [id=" + id +
               ", label='" + label +
               ']';
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
        int result = getId();
        result = 31 * result + getLabel().hashCode();
        return result;
    }
}
