package com.couponsTest.couponDemo.controller;


import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCampaignNotFoundException;
import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCreationException;
import com.couponsTest.couponDemo.couponService.MarketingCampaignService;
import com.couponsTest.couponDemo.entity.Brand;
import com.couponsTest.couponDemo.entity.MarketingCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 * MarketingCampaignController class acts as the REST-API for the Marketing Campaigns.
 * It provides the following functionality:
 * 1.) Create a new campaign
 * 2.) Change or set the name for a campaign
 * 3.) Retrieve an existing campaign
 * @author Thomas.Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/MarketingCampaignService")
public class MarketingCampaignController {
    @Autowired
    private MarketingCampaignService marketingCampaignService;

    public MarketingCampaignController(MarketingCampaignService marketingCampaignService){
        this.marketingCampaignService = marketingCampaignService;
    }

    /**
     * Create a new campaign. The UUID will be automatically generated.
     * Note that campaign will be created without a campaign name. Has to be set with the method setNameForCampaign
     * @param startDateString The desired start date of the campaign in german date format (day-month-year)
     * @param endDateString The desired end date of the campaign. Also in german date format
     * @param brand The brand to promote in this campaign. Note that brand is an ENUM and has a set range. More information in Brand class
     * @return If creation of a new campaign is successful the new campaign will be returned to the client
     * @throws ParseException if the Date format is not correct. Date format must be "dd-MM-yyyy"
     * @throws MarketingCreationException if start date > end date. Logical error.
     */
    @PostMapping(path = "{startDateString}/{endDateString}/{brand}")
    public MarketingCampaign createCampaign(@PathVariable("startDateString") String startDateString,
                                            @PathVariable("endDateString") String endDateString,
                                            @PathVariable("brand")String brand) throws ParseException {
        Date startDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        return marketingCampaignService.createNewCampaign(startDate, endDate, brand);
    }

    /**
     * Set or change the name of existing marketing campaign
     * @param campaignName the desired name for the campaign
     * @param id the UUID of the existing id
     * @return If changing or setting the name is successful the patched campaign will be returned
     * @throws MarketingCampaignNotFoundException if UUID is wrong or doesn't exist
     */
    @PatchMapping(path = "{campaignName}/{id}")
    public MarketingCampaign setNameForCampaign(@PathVariable("campaignName") String campaignName, @PathVariable("id") String id){
        return marketingCampaignService.setNameCampaign(campaignName,id);
    }

    /**
     * Retrieve the campaign from the database with the id
     * @param id the UUID of the campaign
     * @return the campaign object with the corresponding id
     * @throws MarketingCampaignNotFoundException if UUID is wrong or doesn't exist
     */
    @GetMapping(path = "{id}")
    public MarketingCampaign getCampaign(@PathVariable("id") String id){
        return marketingCampaignService.getCampaignById(id);
    }

    @CrossOrigin
    @GetMapping
    public List<MarketingCampaign> getAllCampaign(){
        return marketingCampaignService.getAllCampaings();
    }
}
