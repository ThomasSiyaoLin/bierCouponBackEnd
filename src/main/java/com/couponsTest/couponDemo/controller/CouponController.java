package com.couponsTest.couponDemo.controller;

import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCampaignExpiredException;
import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCampaignNotFoundException;
import com.couponsTest.couponDemo.couponService.CouponService;
import com.couponsTest.couponDemo.entity.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * CouponController class acts as the REST-API for creating and checking coupons.
 * It provides the following functionality:
 * 1.) Create a number of coupons
 * 2.) Redeem a coupon to a user
 * 3.) Check if a coupon is still valid
 * @author Thomas.Lin
 */

@RestController
@RequestMapping("/CouponValidationService")
public class CouponController {

    @Autowired
    private CouponService couponService;


    public CouponController(CouponService couponService){
        this.couponService =couponService;
    }

    /**
     * Return a set of unique coupons for a existing marketing campaign.
     * If not successful an exception will be thrown
     * @param marketingId The UUID of the existing campaign
     * @param amount The amount of how many coupons have to be generated
     * @param startValue The minimum (greater than) value of the UUID
     * @return A collection of all the created coupons. Returned as an array list <coupon>
     * @throws MarketingCampaignExpiredException
     * @throws MarketingCampaignNotFoundException
      */
    @CrossOrigin
    @PostMapping(path = "{marketingId}/{amount}/{startValue}")
    public ArrayList<Coupon> createCoupon(@PathVariable("marketingId") String marketingId,
                                          @PathVariable("amount") int amount,
                                          @PathVariable("startValue") String startValue)
    {
        ArrayList<Coupon> newCouponList = couponService.addCoupon(marketingId, amount, startValue);
        return newCouponList;
    }

    /**
     * Check if a given coupon ID is valid.
     * If there are any complications an exception will be thrown
     * @param couponID The UUID for the coupon to check
     * @return If successful a coupon object will be returned with the equal coupon ID.
     */
    @CrossOrigin
    @GetMapping(path = "{couponID}")
    public Coupon checkValid(@PathVariable("couponID") String couponID) {
        Coupon couponToCheck =  couponService.checkCoupon(couponID);
        return couponToCheck;
    }

    /**
     * Assign one coupon to an existing User.
     * If this process fails an exception will be thrown with further details
     * @param couponId The ID of the coupon which the user desires to redeem
     * @param userId The ID of the user itself
     * @return If successful a coupon object will be returned with the equal coupon ID and updated attributes.
     */
    @CrossOrigin
    @PatchMapping(path = "{couponId}/{userId}")
    public Coupon redeemCode(@PathVariable("couponId") String couponId, @PathVariable("userId") String userId)  {

        Coupon coupontoRedeem = couponService.redeemCode(couponId, userId);
        return coupontoRedeem;

    }
}
