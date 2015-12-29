package com.junjunguo.tsag.model;

import java.util.Date;
import java.util.List;

/**
 * This file is part of tsag.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 29/10/15.
 */
public class UT extends User {
    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public Date getBirth() {
        return null;
    }

    @Override
    public Date getRegisteredTime() {
        return null;
    }

//    @Override
    public List<Tag> getTags() {
        return null;
    }


}
