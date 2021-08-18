package com.couponsTest.couponDemo.dao;


import com.couponsTest.couponDemo.couponExceptionHandler.MarketingCampaignNotFoundException;
import com.couponsTest.couponDemo.entity.MarketingCampaign;
import com.couponsTest.couponDemo.repository.MarketingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Data access object to perform CRUD operation which are defined by the DAO Interface.
 * Uses the repository interface to interact with the database
 * @author Thomas.Lin
 */


@Component
public class MarketingDao implements Dao <MarketingCampaign> {

    @Autowired
    private final MarketingRepository marketingRepository;

    public MarketingDao(MarketingRepository marketingRepository) {
        this.marketingRepository = marketingRepository;
    }

    @Override
    public Optional<MarketingCampaign> get(String id) {
        Optional<MarketingCampaign> campaignToReturn = marketingRepository.findById(id);
        if(campaignToReturn.isEmpty())
            throw new MarketingCampaignNotFoundException(id);
        return campaignToReturn;
    }

    @Override
    public List<MarketingCampaign> getAll() {
        return null;
    }

    @Override
    public void save(MarketingCampaign t) {
        marketingRepository.save(t);
    }

    @Override
    public void update(String id, MarketingCampaign toUpdate) {
        if(marketingRepository.existsById(id)){
            marketingRepository.delete(marketingRepository.findById(id).get());
            return;
        }
        System.out.println("Id Could not be found");
    }

    @Override
    public void delete(MarketingCampaign t) {
        if(marketingRepository.existsById(t.getCampaignID()))
            marketingRepository.delete(t);
    }

    public boolean isCampaignValid(String id){
        return marketingRepository.existsById(id);
    }
}
