package com.couponsTest.couponDemo.couponExceptionHandler;

public class InvalidRequestException extends CustomExceptions {
    public InvalidRequestException(String id) {
        super(id);
    }
    @Override
    public String getMessage(){
        return "One of the used parameters was wrong";
    }
}
