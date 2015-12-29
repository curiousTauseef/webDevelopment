package com.junjunguo.springmvc.dao;

import java.util.List;

import com.junjunguo.springmvc.model.UserProfile;


public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);

    UserProfile findByTypeInitialized(String type);

}
