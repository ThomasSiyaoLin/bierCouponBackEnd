package com.couponsTest.couponDemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private Date registerDate;



    public User(String userId, String firstName, String lastName) {
        this. userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        registerDate = new Date();
    }

    public User() {

    }
}


