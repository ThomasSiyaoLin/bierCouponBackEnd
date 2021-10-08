package com.couponsTest.couponDemo.couponExceptionHandler;

public class BrandCreationException extends CustomExceptions {
    public BrandCreationException(String id) {
        super(id);
    }

    @Override
    public String getMessage() {
        return "The " + id + " already exists. Brand was not created";
    }
}
