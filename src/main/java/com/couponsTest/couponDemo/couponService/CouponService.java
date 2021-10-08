package com.couponsTest.couponDemo.couponService;


import com.couponsTest.couponDemo.couponExceptionHandler.*;
import com.couponsTest.couponDemo.dao.CouponDao;
import com.couponsTest.couponDemo.entity.Coupon;
import com.couponsTest.couponDemo.entity.MarketingCampaign;
import com.couponsTest.couponDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;

/**
 * Coupon Service handles all the logic to fulfill the clients request.
 * The request are received from the coupon controller class.
 * To see which exceptions are thrown please refer to the coupon controller class
 * @author Thomas.Lin
 */

@Service
public class CouponService {
    @Autowired
    private final CouponDao couponDao;
    private final MarketingCampaignService marketingCampaignService;
    private final UserService userService;



    public CouponService(CouponDao couponDao, MarketingCampaignService marketingDao, UserService nutzerDao) {
        this.couponDao = couponDao;
        this.marketingCampaignService = marketingDao;
        this.userService = nutzerDao;
    }

    /**
     * This method adds the specified amount of coupons to the coupon database
     * @param marketingId the campaign the coupons are created for
     * @param amount amount of coupons to create
     * @param startValue the value every coupon has to be greater than
     * @return The created set of coupon in an array list container object
     */
    public ArrayList<Coupon> addCoupon(String marketingId, int amount, String startValue){

        //Check if campaign is even in the database. If not throw an exception to the client.
        Optional<MarketingCampaign> marketingCampaign = marketingCampaignService.getOptionalMarketing(marketingId);
        if(marketingCampaign.isEmpty())
            throw new MarketingCampaignNotFoundException(marketingId);
        if(marketingCampaign.get().getEndDate().before(new Date()))
            throw new MarketingCampaignExpiredException(marketingId, marketingCampaign.get().getEndDate());
        if(amount < 0)
            throw new InvalidRequestException(marketingId);

        //This section generates and saves the coupon with the given paramenters from the method signature
        //The generation could create invalid coupons. Invalid meaning that the generated coupon is a duplicate
        //If this occurs it will try and generate another one until it succeeds to generate a valid one (non-duplicate)
        ArrayList<Coupon> couponCollection = new ArrayList<>();     //Create the coupons the client ordered and return them later as an ArrayList
        Coupon.setCurrentStartId(startValue); //Set up the desired start Value of the coupons

        for(int i = 0; i < amount; i++){
            Coupon newCoupon = new Coupon(marketingId);
            while(couponDao.isCouponPresent(newCoupon))
                newCoupon = new Coupon(marketingId);
            couponCollection.add(newCoupon);
        }
        couponCollection.forEach((Coupon coupon) -> couponDao.save(coupon));
        return couponCollection;
    }


    public Coupon checkCoupon(String couponId){
        Optional<Coupon> coupon = this.couponDao.get(couponId);
        return coupon.get();
    }

    /**
     * A method to redeem a coupon code for a user
     * @param couponId The UUID which a user wants to redeem
     * @param userId The UUID for the user to append a coupon to a user
     * @return The successfully redeemed coupon
     */
    public Coupon redeemCode(String couponId, String userId) {
        Coupon couponToRedeem = checkCoupon(couponId); //check Coupon acts like a validation check. If the Coupon id is invalid it will throw an excpetion
        Date stampDate = new Date();
        MarketingCampaign campaignToRedeem = marketingCampaignService.getCampaignById(couponToRedeem.getCampaignID());

        if(couponToRedeem.isUsed())
            throw new CouponAlreadyUsedException(couponId);
        if(campaignToRedeem.getEndDate().before(stampDate))
            throw new MarketingCampaignExpiredException(campaignToRedeem.getCampaignID(), campaignToRedeem.getEndDate());

        User user = null;
        try {
            user = userService.getUser(userId);
        }
        catch(UserNotFoundException userNotFoundException){
            user = userService.createUser(userId);
        }

        couponToRedeem.setUserID(user.getUserId());
        couponToRedeem.setUsed(true);
        this.couponDao.save(couponToRedeem);
        return couponToRedeem;
    }







}
