package com.couponsTest.couponDemo.couponExceptionHandler;

import java.util.Date;

public class MarketingCreationException extends CustomExceptions{
    private Date startDate;
    private Date endDate;
    public MarketingCreationException(String id, Date startDate, Date endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String getMessage(){
        if(startDate.after(endDate))
            return "The creation of the campaign with the id: " + id + " failed, because start Date was after End Date." + startDate + " " +endDate;
        return "The creation of the campaign with the id: " + id + " failed, because brand doesn't exist";
    }
}
