package com.junjunguo.tsag.service;

import com.junjunguo.tsag.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserByEmail(String email) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getEmail() == email) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(String email) {
        return findByEmail(email) != null;
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Johan", "johan@a.a", "china", "password"));
        users.add(new User("Ola", "ola@a.a", "china", "password"));
        users.add(new User("William", "william@a.a", "china", "password"));
        users.add(new User("Tor", "tor@a.a", "china", "password"));
        return users;
    }
}
