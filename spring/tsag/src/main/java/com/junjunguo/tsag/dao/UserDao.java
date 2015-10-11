package com.junjunguo.tsag.dao;

import com.junjunguo.tsag.model.User;

import java.util.List;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 11, 2015.
 */
public interface UserDao {
    User findByEmail(String email);

    User findByName(String name);

    void saveUser(User user);

    void deleteUserByEmail(String email);

    List<User> findAllUsers();
}
