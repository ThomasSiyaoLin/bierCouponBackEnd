package com.couponsTest.couponDemo.entity;

import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCreationException;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class MarketingCampaign {
    @Id
    private String campaignID;
    private String campaignName;
    private Date startDate;
    private Date endDate;
    private String brand;

    //Constructor for creating a new campaign with the marketing Service class
    //Note that campaign name is not set here to avoid long URI
    public MarketingCampaign( Date startDate, Date endDate, String brand){
        if(startDate.after(endDate))
            throw new MarketingCreationException(campaignID, startDate, endDate);
        this.campaignID = UUID.randomUUID().toString();
        this.startDate = startDate;
        this.endDate = endDate;
        this.brand = brand;
    }

    public MarketingCampaign(){

    }
}
