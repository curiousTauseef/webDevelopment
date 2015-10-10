package com.junjunguo.tsag.service;

import com.junjunguo.tsag.model.User;

import java.util.List;


public interface UserService {

    User findByEmail(String email);

    User findByName(String name);

    void addUser(User user);

    void updateUser(User user);

    void deleteUserByEmail(String email);

    List<User> findAllUsers();

    boolean isUserExist(String email);

}
