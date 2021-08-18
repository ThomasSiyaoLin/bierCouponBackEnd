package com.couponsTest.couponDemo.couponExceptionHandler;

public class CouponAlreadyUsedException extends CustomExceptions{
    public CouponAlreadyUsedException(String id) {
        super(id);
    }
    @Override
    public String getMessage() {
        return "Coupon wiht the ID " + id + "already used by another User";
    }
}
