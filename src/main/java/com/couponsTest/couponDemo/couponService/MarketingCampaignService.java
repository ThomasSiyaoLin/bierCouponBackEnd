package com.couponsTest.couponDemo.couponService;

import com.couponsTest.couponDemo.dao.MarketingDao;
import com.couponsTest.couponDemo.entity.Brand;
import com.couponsTest.couponDemo.entity.MarketingCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

/**
 * Marketing campaign service acts as the interface to communicate with the marketing database table
 * You can perform the basic CRUD operations with this class.
 * @author Thomas.Lin
 */

@Service
public class MarketingCampaignService {
    @Autowired
    private final MarketingDao marketingDao;

    public MarketingCampaignService(MarketingDao marketingDao){
        this.marketingDao = marketingDao;
    }

    /**
     * A method to create new campaign
     * @param startDate of the marketing campaign
     * @param endDate of the marketing campaign
     * @param brand Is an ENUM and therefore has a set amount of value. More information in the Brand class itself
     * @return If successful the marketing object will be returned
     * To see what exceptions will be thrown, please refer to the marketing controller class
     */
    public MarketingCampaign createNewCampaign( Date startDate, Date endDate, Brand brand) {
        MarketingCampaign newCampaign = new MarketingCampaign( startDate, endDate, brand);
        while(marketingDao.isCampaignValid(newCampaign.getCampaignID()))
            newCampaign = new MarketingCampaign( startDate, endDate, brand);
        marketingDao.save(newCampaign);
        return newCampaign;
    }

    public MarketingCampaign setNameCampaign(String name, String id){
        MarketingCampaign changedCampaign = getCampaignById(id);
        changedCampaign.setCampaignName(name);
        marketingDao.save(changedCampaign);
        return changedCampaign;

    }

    public MarketingCampaign getCampaignById(String id){
        return marketingDao.get(id).get();
    }

    public Optional<MarketingCampaign> getOptionalMarketing(String id){
        return marketingDao.get(id);
    }

    public boolean isCampaignValid(String id){
        return marketingDao.isCampaignValid(id);
    }

}
