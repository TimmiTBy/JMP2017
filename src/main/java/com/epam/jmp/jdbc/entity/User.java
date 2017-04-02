package com.epam.jmp.jdbc.entity;

import java.util.Date;

/**
 * Created by Ales on 02.04.2017.
 */
public class User {

    private int id;
    private String firstName;
    private String surName;
    private Date birthday;

    public User(int id, Date birthday) {
        this.id = id;
        this.birthday = birthday;
    }

    public User(int id, String firstName, String surName) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
