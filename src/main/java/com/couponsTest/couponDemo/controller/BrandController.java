package com.couponsTest.couponDemo.controller;


import com.couponsTest.couponDemo.couponService.BrandService;
import com.couponsTest.couponDemo.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/BrandService")
public class BrandController {

    @Autowired
    private BrandService brandService;

    public BrandController(BrandService brandService){this.brandService = brandService;}

    @CrossOrigin
    @PostMapping(path = "{brandName}")
    public String createBrand(@PathVariable("brandName")String brandName){
        return brandService.createBrand(brandName).getName();
    }

    @CrossOrigin
    @GetMapping(path = "{brandName}")
    public String getBrand(@PathVariable("brandName")String brandName){
        return brandService.getBrand(brandName).getName();
    }

    @CrossOrigin
    @GetMapping
    public List<String> getAllBrands() {
        List<Brand> brands = brandService.getAllBrand();
        List<String> brandsString = new ArrayList<String>();
        for(Brand brand : brands){
            brandsString.add(brand.getName());
        }
        return brandsString;
    }
}
