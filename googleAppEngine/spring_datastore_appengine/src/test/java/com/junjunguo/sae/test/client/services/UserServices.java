package com.junjunguo.sae.test.client.services;

import com.junjunguo.sae.test.client.model.User;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface UserServices {

    /**
     * this method is for test only
     */
    List<User> listAllUsers();

    /**
     * @param name of the user
     * @return first user with the matching name
     */
    User getUserByName(String name);

    /**
     * @param email
     * @return user by given email
     */
    User getUserByEmail(String email);

    /**
     * @param user the user
     * @return a feedback message
     */
    String createUser(User user);

    /**
     * @param user the user
     * @return a feedback message
     */
    String updateUser(User user);

    /**
     * @param email of the user
     * @return a feedback message
     */
    String deleteUserByEmail(String email);
}
