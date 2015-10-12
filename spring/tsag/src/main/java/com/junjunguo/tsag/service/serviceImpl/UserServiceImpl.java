package com.junjunguo.tsag.service.serviceImpl;

import com.junjunguo.tsag.dao.UserDao;
import com.junjunguo.tsag.model.User;
import com.junjunguo.tsag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        System.out.println("DAO user dao save user:" + user.toString());
        userDao.saveUser(user);
    }

    public void updateUser(User user) {
        User entity = userDao.findByEmail(user.getEmail());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setBirth(user.getBirth());
            entity.setCountry(user.getCountry());
            entity.setPassword(user.getPassword());
            entity.setRegisteredTime(user.getRegisteredTime());
        }
    }

    public void deleteUserByEmail(String email) {
        userDao.deleteUserByEmail(email);
    }

    public boolean isUserExist(String email) {
        System.out.println("is user exist with email " + email);
        return findByEmail(email) != null;
    }

//    private static List<User> populateDummyUsers() {
//        List<User> users = new ArrayList<User>();
//        users.add(new User("Johan", "johan@a.a", "china", "password"));
//        users.add(new User("Ola", "ola@a.a", "china", "password"));
//        users.add(new User("William", "william@a.a", "china", "password"));
//        users.add(new User("Tor", "tor@a.a", "china", "password"));
//        return users;
//    }
}
