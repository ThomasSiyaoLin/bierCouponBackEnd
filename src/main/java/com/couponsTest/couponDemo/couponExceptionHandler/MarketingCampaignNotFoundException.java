package com.couponsTest.couponDemo.couponExceptionHandler;

public class MarketingCampaignNotFoundException extends CustomExceptions {
    public MarketingCampaignNotFoundException(String id) {
        super(id);
    }

    @Override
    public String getMessage() {
        return "No active campaign with the id " + id;
    }
}

