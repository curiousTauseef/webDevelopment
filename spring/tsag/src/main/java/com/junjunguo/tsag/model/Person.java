package com.junjunguo.tsag.model;

import javax.persistence.*;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@Entity
@Table(name = "person")
public class Person {
    private long id;
    private String username, password, email;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
