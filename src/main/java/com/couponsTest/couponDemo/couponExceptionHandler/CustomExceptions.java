package com.couponsTest.couponDemo.couponExceptionHandler;

import lombok.Data;

@Data
public abstract class CustomExceptions extends RuntimeException{
    protected String id;

    protected CustomExceptions(String id){
        this.id = id;
    }


}
