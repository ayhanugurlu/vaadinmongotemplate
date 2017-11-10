package com.au.example.repository.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by Ayhan Ugurlu - (ayhan.ugurlu@odc.com.tr) on 13.09.2017.
 */

public class User {


    @Id
    private String id;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    protected User() {
    }

    public User(String userName, String firstName, String lastName,String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s,userName='%s', firstName='%s', lastName='%s']",
                id, userName, firstName, lastName);
    }


}
