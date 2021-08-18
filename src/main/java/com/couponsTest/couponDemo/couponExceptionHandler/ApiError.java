package com.couponsTest.couponDemo.couponExceptionHandler;
import java.time.ZonedDateTime;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ApiError is a class to document customized exceptions.
 * Everytime a custom exception is thrown, an api Error Object will be created along with it
 * @author Thomas.Lin
 * */

@Data
public class ApiError {


    private HttpStatus status;
    private CustomExceptions throwable;
    private String message;
    private ZonedDateTime timestamp;


    public ApiError(HttpStatus status, CustomExceptions throwable, ZonedDateTime timestamp, String message ){
        this.message = message;
        this.status = status;
        this.throwable = throwable;
        this.timestamp = timestamp;
    }


}