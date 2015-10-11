package com.junjunguo.tsag.testmodel;

import java.util.Calendar;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;
    private String password;
    private long birth;
    private long registeredTime;

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
        this.registeredTime = registeredtime;

        this.id = (int) (Math.random() * 10000);
    }

    public User() {
        id = (int) (Math.random() * 10000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public void updateUser(User user) {
        setName(user.getName());
        setBirth(user.getBirth());
        setCountry(user.getCountry());
        setPassword(user.getPassword());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
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
                ", registeredTime=" + registeredTime +
                "]";
    }
}
