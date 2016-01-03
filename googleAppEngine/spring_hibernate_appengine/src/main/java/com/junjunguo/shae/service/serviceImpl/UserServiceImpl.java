package com.junjunguo.shae.service.serviceImpl;

import com.junjunguo.shae.dao.UserDao;
import com.junjunguo.shae.model.User;
import com.junjunguo.shae.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public void addUser(User user) {
        userDao.saveUser(user);
    }

    public void updateUser(User user) {
        userDao.saveUser(user);
    }

    public void deleteUserByEmail(String email) {
        userDao.deleteUserByEmail(email);
    }

    public boolean isUserExist(String email) {
        return findByEmail(email) != null;
    }
}