package com.junjunguo.tsag.service;

import com.junjunguo.tsag.model.User;

import java.util.List;


public interface UserService {

    //    User findById(long id);

    User findByEmail(String email);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    //    void deleteUserById(long id);
    void deleteUserByEmail(String email);

    List<User> findAllUsers();

    //    boolean isUserExist(User user);

    boolean isUserExist(String email);

}
