package com.junjunguo.tsag.model;

import java.util.Calendar;

public class User {

    private long id;

    private String name;

    private String email;
    private String country;
    private String password;
    private Calendar birth;
    private Calendar registeredtime;

    private int age;

    private double salary;

    public User() {
        id = 0;
    }

    public User(long id, String name, int age, double salary) {
        this(id, name, "c@china.cn", salary, "password", "China", Calendar.getInstance(), age);
    }

    public User(
            long id, String name, String email, double salary, String password, String country,
            Calendar birth, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.registeredtime = Calendar.getInstance();
        this.password = password;
        this.country = country;
        this.birth = birth;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public Calendar getRegisteredtime() {
        return registeredtime;
    }

    public void setRegisteredtime(Calendar registeredtime) {
        this.registeredtime = registeredtime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        User other = (User) obj;
        if (id != other.id) { return false; }
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id +
               ", name=" + name +
               ", age=" + age +
               ", salary=" + salary +
               ", email='" + email +
               ", country='" + country +
               ", password='" + password +
               ", birth=" + birth +
               ", registeredtime=" + registeredtime +
               ']';
    }
}
