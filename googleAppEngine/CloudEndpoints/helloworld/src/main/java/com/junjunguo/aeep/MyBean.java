package com.junjunguo.aeep;

/**
 * This file is part of helloworld.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 04/01/16.
 * <p/>
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}