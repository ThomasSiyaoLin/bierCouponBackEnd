package com.couponsTest.couponDemo.repository;
import com.couponsTest.couponDemo.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
