package com.junjunguo.tsag.testmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */
public class Tag implements Serializable{
    private int    id;
    private String label;
    private List<User> users = new ArrayList<User>();

    public Tag(String label) {
        this.label = label;
    }

    public Tag() {

    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
            users.remove(user);
        }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
