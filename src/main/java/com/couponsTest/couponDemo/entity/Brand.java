package com.couponsTest.couponDemo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

//ENUM for the diffrent brands

@Entity
public class Brand {



    @Id
    private String name;

    public Brand(String name){this.name = name;}
    public Brand(){
        name = "-1";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}

