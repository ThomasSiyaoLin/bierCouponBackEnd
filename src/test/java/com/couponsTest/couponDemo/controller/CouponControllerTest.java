package com.couponsTest.couponDemo.controller;

import com.couponsTest.couponDemo.dao.CouponDao;
import com.couponsTest.couponDemo.entity.Coupon;
import com.couponsTest.couponDemo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CouponControllerTest {
    @Autowired
    private final CouponController controller;

    public CouponControllerTest(CouponController controller) {
        this.controller = controller;
    }

/*    void testCouponCreation(){
        //test if all the coupons are unique
        List<Coupon> testSet = controller.createCoupon(1, 100, 1000);

    }*/
}