package com.junjunguo.aeep.backend.dao;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 11, 2016.
 */
public class DataCache {
    private static DataCache ourInstance = new DataCache();

    public static DataCache getInstance() {
        return ourInstance;
    }

    private DataCache() {
    }
}
