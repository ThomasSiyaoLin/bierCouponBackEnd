package com.couponsTest.couponDemo.dao;

import com.couponsTest.couponDemo.couponExceptionHandler.CouponNotFoundException;
import com.couponsTest.couponDemo.entity.Coupon;
import com.couponsTest.couponDemo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data access object to perform CRUD operation which are defined by the DAO Interface.
 * Uses the repository interface to interact with the database
 * @author Thomas.Lin
 */


@Component
public class CouponDao implements Dao <Coupon>{

    @Autowired
    private CouponRepository couponRepository;

    public CouponDao(CouponRepository couponRepository){
        this.couponRepository = couponRepository;
    }


    @Override
    public Optional<Coupon> get(String id) {

       Optional<Coupon> toReturn = couponRepository.findById(id);
       if(toReturn.isEmpty())
           throw new CouponNotFoundException(id);
        return couponRepository.findById(id);
    }

    @Override
    public List<Coupon> getAll() {

        return couponRepository.findAll();
    }

    @Override
    public void save(Coupon t) {
        couponRepository.save(t);
    }


    public void update(String id, Coupon toUpdate) {
        //Not used yet
    }

    @Override
    public void delete(Coupon t) {
        //Not used yet
    }

    public boolean isCouponPresent(Coupon coupon){
        Optional<Coupon> toCheck = couponRepository.findById(coupon.getCouponID());
        return toCheck.isPresent();
    }


}
