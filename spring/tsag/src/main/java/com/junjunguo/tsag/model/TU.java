package com.junjunguo.tsag.model;

import java.util.List;

/**
 * This file is part of tsag.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public class TU {
    private int        id;
    private String     label;
    private List<User> users;

    public TU(int id, String label, List<User> users) {
        this.id = id;
        this.label = label;
        this.users = users;
    }

    public TU() {
    }

    public TU(Tag tag) {
        this(tag.getId(), tag.getLabel(), tag.getUsers());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
