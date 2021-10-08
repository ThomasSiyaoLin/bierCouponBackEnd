package com.couponsTest.couponDemo.couponExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZonedDateTime;

/**
 * Custom Rest Exception Handler handles all custom exceptions wherever one is thrown in the programm.
 * Custom exception is an abstract class and the generalization of all specialized custom exceptions.
 * @author Thomas.Lin
 */

@Component
@ControllerAdvice
public class CustomRestExceptionHandler {

    /**
     * A method to handle ALL thrown exceptions.
     * Based on the specific type of the exception the specialized method will be chosen
     * @param ex the exception to handle. By using instanceof this method can identify the specialized exception
     * @return A Response Entity with the API Error as its generic class to respond to the client accordingly
     */
    @ExceptionHandler({CustomExceptions.class})
    public final ResponseEntity<ApiError> handleException(CustomExceptions ex) {

        if (ex instanceof UserNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;

            return handleUserNotFoundException(unfe, status);
        }

        else if (ex instanceof CouponNotFoundException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            CouponNotFoundException unfe = (CouponNotFoundException) ex;

            return handleCouponNotFoundException(unfe, status);
        }

        else if (ex instanceof CouponAlreadyUsedException){
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            CouponAlreadyUsedException unfe = (CouponAlreadyUsedException) ex;

            return  handleCouponAlreadyUsedException(unfe, status);
        }

        else if(ex instanceof MarketingCampaignNotFoundException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            MarketingCampaignNotFoundException unfe = (MarketingCampaignNotFoundException) ex;

            return handleMarketingCampaignNotFoundException(unfe, status);
        }

        else if(ex instanceof MarketingCampaignExpiredException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            MarketingCampaignExpiredException unfe = (MarketingCampaignExpiredException) ex;

            return handleMarketingCampaignExpired(unfe, status);
        }
        else if(ex instanceof MarketingCreationException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            MarketingCreationException unfe = (MarketingCreationException) ex;

            return handleMarketingCreationException(unfe, status);
        }

        else if(ex instanceof InvalidRequestException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            InvalidRequestException unfe = (InvalidRequestException) ex;

            return handleInvalidRequestException(unfe, status);
         }
        else if(ex instanceof BrandCreationException){
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BrandCreationException unfe = (BrandCreationException) ex;

            return handleBrandCreationException(unfe, status);
        }
        
        else if(ex instanceof BrandNotFound){
            HttpStatus status = HttpStatus.NOT_FOUND;
            BrandNotFound unfe = (BrandNotFound) ex; 
            
            return handleBrandNotFoundException(unfe,status); 
        }

        return null;
    }



    // In this section all the specialized methods for each specialized exception is written.
    //The method 'handleExecption' will select one of the methods beneath and return it to the client
    private ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpStatus status) {
        ApiError userNotFoundError = new ApiError(status, ex, ZonedDateTime.now(), "User is not in the database");
        return new ResponseEntity<ApiError>(userNotFoundError, status);
    }

    private ResponseEntity<ApiError> handleMarketingCampaignNotFoundException(MarketingCampaignNotFoundException ex, HttpStatus status) {
        ApiError userNotFoundError = new ApiError(status, ex, ZonedDateTime.now(), "Marketing campaign is not in the database");
        return new ResponseEntity<ApiError>(userNotFoundError, status);
    }

    private ResponseEntity<ApiError> handleCouponNotFoundException(CouponNotFoundException ex, HttpStatus status) {
        ApiError couponNotFoundError = new ApiError(status, ex, ZonedDateTime.now(), "Coupon is not in the database");
        return new ResponseEntity<ApiError>(couponNotFoundError, status);
    }

    private ResponseEntity<ApiError> handleMarketingCampaignExpired(MarketingCampaignExpiredException ex, HttpStatus status) {
        ApiError marketingCampaignExpired = new ApiError(status, ex, ZonedDateTime.now(), "This campaign already expired");
        return new ResponseEntity<ApiError>(marketingCampaignExpired, status);
    }

    private ResponseEntity<ApiError> handleCouponAlreadyUsedException(CouponAlreadyUsedException ex, HttpStatus status){
        ApiError couponAlreadyUsedError = new ApiError(status, ex, ZonedDateTime.now(), "Another Users already reedeemed the this coupon");
        return new ResponseEntity<ApiError>(couponAlreadyUsedError, status);
    }

    private ResponseEntity<ApiError> handleMarketingCreationException(MarketingCreationException ex, HttpStatus status) {
        ApiError creationMarketingFail = new ApiError(status, ex, ZonedDateTime.now(), "Creation of the campaign failed");
        return new ResponseEntity<ApiError>(creationMarketingFail, status);
    }

    private ResponseEntity<ApiError> handleInvalidRequestException(InvalidRequestException ex, HttpStatus status){
        ApiError invalidRequest = new ApiError(status, ex, ZonedDateTime.now(), "The amount of coupons must be greater then 0");
        return new ResponseEntity<ApiError>(invalidRequest,status);
    }

    private ResponseEntity<ApiError>  handleBrandCreationException(BrandCreationException ex, HttpStatus status){
        ApiError brandCreationFail = new ApiError(status, ex, ZonedDateTime.now(), "Brand already exists");
        return new ResponseEntity<ApiError>(brandCreationFail,status);
    }

    private ResponseEntity<ApiError>  handleBrandNotFoundException(BrandNotFound ex, HttpStatus status){
        ApiError brandCreationFail = new ApiError(status, ex, ZonedDateTime.now(), "Brand not found");
        return new ResponseEntity<ApiError>(brandCreationFail,status);
    }

}




