package com.junjunguo.tsag.service;

import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.User;

import java.util.List;


public interface UserService {

    User findByEmail(String email);

    User findByName(String name);

    Tag findByTag(String label);

    Tag findByTagId(int id);

    void addTag(String label);

    boolean hasTag(String label);

    void addUser(User user);

    void updateUser(User user);

    void deleteUserByEmail(String email);

    List<User> findAllUsers();

    List<Tag> findAllTags();

    boolean isUserExist(String email);

}
