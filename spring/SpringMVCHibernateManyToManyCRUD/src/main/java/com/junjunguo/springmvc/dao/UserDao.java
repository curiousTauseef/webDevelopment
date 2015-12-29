package com.junjunguo.springmvc.dao;

import java.util.List;

import com.junjunguo.springmvc.model.User;
import com.junjunguo.springmvc.model.UserProfile;


public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();



}

