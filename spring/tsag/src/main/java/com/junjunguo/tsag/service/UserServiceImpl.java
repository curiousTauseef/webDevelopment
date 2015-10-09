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

    //    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    //    public User findById(long id) {
    //        for (User user : users) {
    //            if (user.getId() == id) {
    //                return user;
    //            }
    //        }
    //        return null;
    //    }

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

    public void saveUser(User user) {
        //        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    //    public void deleteUserById(long id) {
    //
    //        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
    //            User user = iterator.next();
    //            if (user.getId() == id) {
    //                iterator.remove();
    //            }
    //        }
    //    }

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
        //        users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
        //        users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
        //        users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
        //        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
        users.add(new User("Johan", "johan@a.a", "china", "password"));
        users.add(new User("Ola", "ola@a.a", "china", "password"));
        users.add(new User("William", "william@a.a", "china", "password"));
        users.add(new User("Tor", "tor@a.a", "china", "password"));
        return users;
    }
}
