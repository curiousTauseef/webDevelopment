package com.junjunguo.sae.model;

import java.util.Date;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 30/10/15.
 */
public class Owner extends User {

    /**
     * Gets password.
     *
     * @return Value of password.
     */
    @Override
    public String getPassword() {
        return "";
    }

    /**
     * Gets birth.
     *
     * @return Value of birth.
     */
    @Override
    public Date getBirth() {
        return null;
    }
}
