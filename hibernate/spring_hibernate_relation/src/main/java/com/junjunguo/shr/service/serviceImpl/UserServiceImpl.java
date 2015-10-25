package com.junjunguo.shr.service.serviceImpl;

import com.junjunguo.shr.dao.UserDao;
import com.junjunguo.shr.model.User;
import com.junjunguo.shr.service.UserService;
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
        User entity = userDao.findByEmail(user.getEmail());
        if (entity != null) {
            userDao.saveUser(entity);
        }
    }

    public void deleteUserByEmail(String email) {
        userDao.deleteUserByEmail(email);
    }

    public boolean isUserExist(String email) {
        return findByEmail(email) != null;
    }
}