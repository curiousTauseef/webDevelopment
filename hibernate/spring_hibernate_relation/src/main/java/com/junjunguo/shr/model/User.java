package com.junjunguo.shr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junjunguo.shr.util.MyDate;

import javax.persistence.*;
import java.io.Serializable;
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
public class User implements Serializable {
    @Column(name = "NAME",
            nullable = true,
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
    @JsonIgnore
//    @JsonBackReference
    @OneToMany(mappedBy = "owner")
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
     */
    public User() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (getCountry() != null ? !getCountry().equals(user.getCountry()) : user.getCountry() != null) return false;
        if (getGender() != null ? !getGender().equals(user.getGender()) : user.getGender() != null) return false;
        if (getBirth() != null ? !getBirth().equals(user.getBirth()) : user.getBirth() != null) return false;
        if (!getRegisteredtime().equals(user.getRegisteredtime())) return false;
        return !(getVideos() != null ? !getVideos().equals(user.getVideos()) : user.getVideos() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getBirth() != null ? getBirth().hashCode() : 0);
        result = 31 * result + getRegisteredtime().hashCode();
        result = 31 * result + (getVideos() != null ? getVideos().hashCode() : 0);
        return result;
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
     * add a new video to video list.
     *
     * @param video New value of videos.
     */
    public void addVideos(Video video) { this.videos.add(video); }

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
