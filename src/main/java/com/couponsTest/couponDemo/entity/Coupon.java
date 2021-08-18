package com.couponsTest.couponDemo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.Integer;
import java.util.Random;
import java.util.UUID;




//This class defines what attributes define a coupon. Note that the controller class a method to create
//a certain amount of coupons at once.
@Data
@Entity
public class Coupon  {
    private final static int maxAmountCoupons = Integer.MAX_VALUE/2;
    private final static Random randomNumGenerator = new Random();


    private static String currentStartId;
    public static void setCurrentStartId(String newRunID){
        currentStartId = newRunID;
    }



    @Id
    private String couponID;
    private String campaignID;
    private boolean isUsed;
    private String userID;

    //Valid constructor for generating a coupon with a valid campaign
    public Coupon(String campaign_ID) {
        String newRandomId = UUID.randomUUID().toString();
        while(currentStartId.compareTo(newRandomId) < 0)
            newRandomId = UUID.randomUUID().toString();
        couponID = newRandomId;
        campaignID = campaign_ID;
        userID = "-1";


    }

    //Constructor if Invalid marketingID is used as a Parameter
    public Coupon() {
        this.couponID = "-1";
        this.campaignID = "-1";
        this.isUsed = false;
    }



    @Override
    public String toString() {
        return "Coupon " + couponID;
    }
}


