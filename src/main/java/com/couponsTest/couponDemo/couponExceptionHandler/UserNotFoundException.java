package com.couponsTest.couponDemo.couponExceptionHandler;


public class UserNotFoundException extends CustomExceptions{


    public UserNotFoundException(String id) {
        super(id);
    }


    @Override
    public String getMessage(){
        return "User " + id + " not found";
    }
}
