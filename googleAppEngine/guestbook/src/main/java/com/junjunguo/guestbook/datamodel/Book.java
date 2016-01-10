package com.junjunguo.guestbook.datamodel;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * This file is part of guestbook.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 10/01/16.
 */
@Entity
public class Book {
    @Id
    private Long id;
    private String    name;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
