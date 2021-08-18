package com.couponsTest.couponDemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//ENUM for the diffrent brands

@Entity
public enum Brand {
    BIERFAX,
    SELTERS,
    FRANKFURTER;


    @Id
    private String name;

    public void setName(){
        if(this==BIERFAX)
            this.name = "Bierfax";
        else if(this==SELTERS)
            this.name = "Selters";
        else if(this == FRANKFURTER)
            this.name = "Frankfurter";
    }

    public String getName(){
        return this.name;
    }

}

