package com.junjunguo.tsag.service.serviceImpl;

import com.junjunguo.tsag.dao.DaoPerson;
import com.junjunguo.tsag.model.Person;
import com.junjunguo.tsag.service.UserService;
import com.junjunguo.tsag.util.Constant;
import com.junjunguo.tsag.util.MyJsonParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 07, 2015.
 */
@WebService(endpointInterface = "com.junjunguo.tsag.service.UserService",
            targetNamespace = Constant.SERVER_URL)
@Service
public class UserServiceImpl implements UserService {
    private DaoPerson daoPerson;

    @Transactional
    public String register(String username, String email, String password) {
        Person person = this.daoPerson.getByEmail(email);
        if (person != null) {
            return new MyJsonParser("register", "success").toString();
        }
        person = new Person();
        person.setEmail(email);
        person.setUsername(username);
        person.setPassword(password);
        this.daoPerson.add(person);
        return new MyJsonParser("register", "success").toString();
    }

    public String login(String email, String password) {
        Person person = this.daoPerson.getByEmail(email);
        if (person == null) {
            return new MyJsonParser("login", "Incorrect username or password").toString();
        }
        if (!person.getPassword().equals(password)) {
            return new MyJsonParser("login", "Incorrect username or password").toString();
        }
        try {
            JSONObject job = new JSONObject();
            job.put("name", person.getUsername());
            return new MyJsonParser("login", "success", "JSONObject", job.toString()).toString();
        } catch (Exception e) {
            e.fillInStackTrace();
            return new MyJsonParser("login", "Server Error").toString();
        }

    }

    public String checkLogin(
            @WebParam(name = "email")
            String email) {
        return null;
    }
}
