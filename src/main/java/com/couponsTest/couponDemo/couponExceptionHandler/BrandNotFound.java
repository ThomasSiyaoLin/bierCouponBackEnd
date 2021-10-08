package com.couponsTest.couponDemo.couponExceptionHandler;

public class BrandNotFound extends CustomExceptions{
    public BrandNotFound(String id) {
        super(id);
    }
    @Override
    public String getMessage() {
        return "The Brand " + id + " could not be found";
    }

}
