package com.junjunguo.tsag.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
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
    private int id;
    @Column(name = "LABEL", nullable = false, columnDefinition = "VARCHAR(255)")
    private String label;
    @ManyToMany
    private List<User> users;

    public Tag(String label) {
        this.label = label;
    }

    public Tag() {

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
    void setLabel(String label) {
        this.label = label;
    }
}
