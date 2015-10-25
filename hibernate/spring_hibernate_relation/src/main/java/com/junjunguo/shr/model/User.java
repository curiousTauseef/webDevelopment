package com.junjunguo.shr.model;

import com.junjunguo.shr.util.MyDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Entity
@Table(name = "USER")
public class User {
    @Column(name = "NAME",
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String      name;
    @Id
    @Column(name = "EMAIL",
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String      email;
    @Column(name = "PASSWORD",
            nullable = false,
            columnDefinition = "VARCHAR(128)")
    private String      password;
    @Column(name = "COUNTRY",
            nullable = true,
            columnDefinition = "VARCHAR(128)")
    private String      country;
    @Column(name = "GENDER",
            nullable = true,
            columnDefinition = "VARCHAR(10)")
    private String      gender;
    @Column(name = "BIRTH",
            nullable = true,
            columnDefinition = "DATE")
    private Date        birth;
    @Column(name = "REGISTEREDTIME",
            nullable = false,
            columnDefinition = "DATETIME")
    private Date        registeredtime;

    @OneToMany(fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_EMAIL")
    private List<Video> videos;

    /**
     * @param name     user name
     * @param email    user email
     * @param password user password
     */
    public User(String name, String email, String password) {
        this(name, email, password, "", "", Calendar.getInstance().getTime());
    }

    /**
     * @param name     user name
     * @param email    user email
     * @param password user password
     * @param country  user come from
     * @param gender   user gender MALE,FEMALE
     * @param birth    user birthday
     */
    public User(String name, String email, String password, String country, String gender, Date birth) {
        this(name, email, password, country, gender, birth, Calendar.getInstance().getTime());
    }

    /**
     * @param name           user name
     * @param email          user email
     * @param password       user password
     * @param country        user come from
     * @param gender         user gender MALE,FEMALE
     * @param birth          user birthday
     * @param registeredtime registered time cannot be set, auto generate
     */
    private User(String name, String email, String password, String country, String gender, Date birth,
            Date registeredtime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.birth = birth;
        this.registeredtime = registeredtime;
    }

    /**
     * auto generate registered time
     *
     * @param philip
     * @param s
     * @param norway
     * @param s1
     * @param date
     */
    public User(String philip, String s, String norway, String s1, Date date) {
        this.registeredtime = Calendar.getInstance().getTime();
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new registeredtime.
     *
     * @param registeredtime New value of registeredtime.
     */
    public void setRegisteredtime(Date registeredtime) {
        this.registeredtime = registeredtime;
    }

    /**
     * Sets new email.
     *
     * @param email New value of email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets new password.
     *
     * @param password New value of password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets new birth.
     *
     * @param birth New value of birth.
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * Gets email.
     *
     * @return Value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return Value of password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets new country.
     *
     * @param country New value of country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets country.
     *
     * @return Value of country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets new gender.
     *
     * @param gender New value of gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets gender.
     *
     * @return Value of gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets birth.
     *
     * @return Value of birth.
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets registeredtime.
     *
     * @return Value of registeredtime.
     */
    public Date getRegisteredtime() {
        return registeredtime;
    }

    @Override
    public String toString() {
        return "User [name=" + name +
               ", email='" + email +
               ", country='" + country +
               ", gender='" + gender +
               ", password='" + password +
               ", birth=" + new MyDate().getDateString(birth) +
               ", registeredtime=" + new MyDate().getDateString(registeredtime) +
               ']';
    }


    /**
     * Sets new videos.
     *
     * @param videos New value of videos.
     */
    public void setVideos(List<Video> videos) { this.videos = videos; }

    /**
     * Gets videos.
     *
     * @return Value of videos.
     */
    public List<Video> getVideos() { return videos; }
}
