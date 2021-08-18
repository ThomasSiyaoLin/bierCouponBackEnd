package com.couponsTest.couponDemo.couponExceptionHandler;

public class CouponNotFoundException extends CustomExceptions{

    public CouponNotFoundException(String id) {
        super(id);

    }

    @Override
    public String getMessage(){
        return "Coupon " + id + " not found";
    }


}


