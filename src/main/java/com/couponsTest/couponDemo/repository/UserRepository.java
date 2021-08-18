package com.couponsTest.couponDemo.repository;

import com.couponsTest.couponDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
