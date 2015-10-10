package com.junjunguo.tsag.model;

import java.util.Calendar;

public class User {
    private static int stid = 0;
    private int obid;
    private String name;
    private String email;
    private String country;
    private String password;
    private long birth;
    private long registeredtime;

    public User(String name, String email, String country, String password) {
        this(name, email, country, password, Calendar.getInstance().getTimeInMillis(),
             Calendar.getInstance().getTimeInMillis());
    }

    public User(String name, String email, String password) {
        this(name, email, "", password);
    }

    public User(String name, String email, String country, String password, long birth) {
        this(name, email, country, password, birth, Calendar.getInstance().getTimeInMillis());
    }

    public User(String name, String email, String country, String password, long birth,
                long registeredtime) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.password = password;
        this.birth = birth;
        this.registeredtime = registeredtime;
        stid += 1;
        obid = stid;
    }

    public User() {
        stid += 1;
        obid = stid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public long getRegisteredtime() {
        return registeredtime;
    }

    public void setRegisteredtime(long registeredtime) {
        this.registeredtime = registeredtime;
    }

    public void updateUser(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setBirth(user.getBirth());
        setCountry(user.getCountry());
        setPassword(user.getPassword());
        setRegisteredtime(user.getRegisteredtime());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (obid ^ (obid >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        User other = (User) obj;
        return (email.equalsIgnoreCase(other.getEmail()));
    }

    @Override
    public String toString() {
        return "User [name=" + name +
               ", email='" + email +
               ", country='" + country +
               ", password='" + password +
               ", birth=" + birth +
               ", registeredtime=" + registeredtime +
               ']';
    }
}
