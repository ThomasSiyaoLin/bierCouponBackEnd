package com.couponsTest.couponDemo.dao;

import com.couponsTest.couponDemo.couponExceptionHandler.BrandNotFound;
import com.couponsTest.couponDemo.couponExceptionHandler.UserNotFoundException;
import com.couponsTest.couponDemo.entity.Brand;
import com.couponsTest.couponDemo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BrandDao implements Dao <Brand>{

    @Autowired
    private BrandRepository brandRepository;

    public BrandDao(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional get(String id) throws UserNotFoundException {
        Optional<Brand> brandToReturn = brandRepository.findById(id);
        if(brandToReturn.isEmpty())
            throw new BrandNotFound(id);
        return brandToReturn;
    }

    @Override
    public List getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void save(Brand brand) {
            brandRepository.save(brand);
    }



    @Override
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }

    public boolean checkDuplicate(String brandName){
        return brandRepository.existsById(brandName);
    }
}
