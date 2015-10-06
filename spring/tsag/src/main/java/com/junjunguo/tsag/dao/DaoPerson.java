package com.junjunguo.tsag.dao;

import com.junjunguo.tsag.model.Person;

/**
 * This file is part of tsag
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 06, 2015.
 */
public interface DaoPerson {
    void add(Person person);

    void delete(Person person);

    void update(Person person);

    Person getByEmail(String email);

    Person getByUsername(String username);
}
