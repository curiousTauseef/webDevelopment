package com.junjunguo.shr.service;

import com.junjunguo.shr.service.model.User;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface UserServices {

    /**
     * this method is for test only
     */
    void listAllUsers();

    /**
     * @param name
     * @return first user with the matching name
     */
    User getUserByName(String name);

    User getUserByEmail(String email);

    /**
     * @param user
     * @return a feedback message
     */
    String createUser(User user);

    /**
     * @param user
     * @return a feedback message
     */
    String updateUser(User user);

    /**
     * @param email
     * @return a feedback message
     */
    String deleteUserByEmail(String email);
}
