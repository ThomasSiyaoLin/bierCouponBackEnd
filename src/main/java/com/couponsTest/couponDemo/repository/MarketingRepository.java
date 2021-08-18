package com.couponsTest.couponDemo.repository;

import com.couponsTest.couponDemo.entity.MarketingCampaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingRepository extends JpaRepository<MarketingCampaign, String> {
}
