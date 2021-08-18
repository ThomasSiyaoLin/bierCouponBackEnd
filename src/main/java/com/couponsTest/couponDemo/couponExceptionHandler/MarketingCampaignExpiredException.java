package com.couponsTest.couponDemo.couponExceptionHandler;

import java.util.Date;

public class MarketingCampaignExpiredException extends CustomExceptions{
    private Date date;
    public MarketingCampaignExpiredException(String id, Date expiredDate) {
        super(id);
        date = expiredDate;

    }

    @Override
    public String getMessage(){
        return "The coupon for this campaign already expired \n"
                + "Expired on: " + date;
    }

}
