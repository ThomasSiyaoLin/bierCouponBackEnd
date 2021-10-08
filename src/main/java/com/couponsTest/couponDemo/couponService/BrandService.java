package com.couponsTest.couponDemo.couponService;


import com.couponsTest.couponDemo.couponExceptionHandler.BrandCreationException;
import com.couponsTest.couponDemo.dao.BrandDao;
import com.couponsTest.couponDemo.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private final BrandDao brandDao;

    public BrandService(BrandDao brandDao) {
        this.brandDao = brandDao;
    }


    public Brand createBrand(String brandName){
        if(brandDao.checkDuplicate(brandName))
            throw new BrandCreationException(brandName);
        Brand newBrand = new Brand(brandName);
        brandDao.save(newBrand);
        return newBrand;
    }

    public Brand getBrand(String brandName){
        Optional<Brand> searchedBrand = brandDao.get(brandName);
        return searchedBrand.get();
    }

    public List<Brand> getAllBrand(){
        return brandDao.getAll();
    }


}
