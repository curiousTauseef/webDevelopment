package com.junjunguo.tsag.model;

import java.util.List;

/**
 * This file is part of tsag.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public class TU extends Tag {
    private List<User> users;

    public TU(int id, String label, List<User> users) {
        super(id, label);
        setUsers(users);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
    }


}
