package com.junjunguo.tsag.service.serviceImpl;

import com.junjunguo.tsag.dao.TagDao;
import com.junjunguo.tsag.dao.UserDao;
import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.User;
import com.junjunguo.tsag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TagDao tagDao;

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public List<Tag> findAllTags() {
        return tagDao.findAllTags();
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public Tag findByTag(String label) {
        return tagDao.findTagByLabel(label);
    }

    public Tag findByTagId(int id) {
        return tagDao.findTagById(id);
    }

    public void addTag(String label) {
        log("add tag: " + label);
        tagDao.saveTag(label);
    }

    public void addUser(User user) {
        log("add user : " + user);
        List<Tag> tags = user.getTags();
        log("tags -1-: " + tags);
        List<Tag> tg = new ArrayList<Tag>();
        for (Tag tag : tags) {
            Tag t = tagDao.findTagByLabel(tag.getLabel());
            if (t == null) {
                Tag nt = new Tag(tag.getLabel());
                log("tag f2 nt: " + nt);
//                nt.addUser(user);
//                log("nt.add f3 user: " + nt);
//                tagDao.saveTag(nt);// auto saved
//                log("tag dao f4 find nt" + tagDao.findTagByLabel(nt.getLabel()));
//                tg.add(tagDao.findTagByLabel(nt.getLabel()));
                tg.add(nt);
            } else {
//                t.addUser(user);
//                tagDao.saveTag(t); // auto saved
//                log("e t: " + t);
                tg.add(t);
            }
        }
        user.setTags(tg);
        userDao.saveUser(user);
    }

    public boolean hasTag(String label) {
        return tagDao.findTagByLabel(label) != null;
    }

    public void updateUser(User user) {
        //        userDao.saveUser(user);
        User entity = userDao.findByEmail(user.getEmail());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setBirth(user.getBirth());
            entity.setCountry(user.getCountry());
            entity.setPassword(user.getPassword());
            entity.setRegisteredTime(user.getRegisteredTime());
            entity.setTags(user.getTags());
        }
    }

    public void deleteUserByEmail(String email) {
        log("delete user by email " + email);
        userDao.deleteUserByEmail(email);
    }

    public boolean isUserExist(String email) {
        log("is user exist with email " + email);
        return findByEmail(email) != null;
    }

    public void log(String s) {
        System.out.println("\n----" + this.getClass().getSimpleName() + " " + s);
    }
}
