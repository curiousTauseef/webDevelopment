package com.junjunguo.tsag.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Size(min = 3, max = 250)
    @Column(name = "username", nullable = false)
    private String username;
    @Size(min = 3, max = 250)
    @Column(name = "password", nullable = false)
    private String password;
    @Size(min = 3, max = 250)
    @Column(name = "email", nullable = false)
    private String email;

    //    @Id
    //    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

/*
mysql> create database tsag_test;
mysql> USE tsag_test
CREATE TABLE person(
     id INT NOT NULL auto_increment,
     username VARCHAR(250) NOT NULL,
     password VARCHAR(250) NOT NULL,
     email VARCHAR(250) NOT NULL,
     PRIMARY KEY (id)
 );
 */