package com.junjunguo.aeep.model;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
public enum Gender {
    /**
     * Male.
     */
    MALE("MALE"),
    /**
     * Female.
     */
    FEMALE("FEMALE"),
    /**
     * Gender is not known, or not specified.
     */
    UNKNOWN("UNKNOWN");

    private final String name;

    private Gender(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
