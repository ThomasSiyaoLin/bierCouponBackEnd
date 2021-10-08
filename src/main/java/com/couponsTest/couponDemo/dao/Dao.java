package com.couponsTest.couponDemo.dao;

import com.couponsTest.couponDemo.couponExceptionHandler.UserNotFoundException;


import java.util.List;
import java.util.Optional;

/**
 * An Interface class to fore implementation of CRUD operations for the marketing, user and coupon database tables
 * @param <T> Can be coupon, marketing camping or the user Class, which are defined in the Entity package
 *         Note that the technical implementation of these operations are done in the repository interface class, which
 *           are inheriting from the JPA Repository
 */

public interface Dao <T>  {
    Optional<T> get(String id) throws UserNotFoundException;

    List<T> getAll();

    void save(T c);

    //void update(String id, T toUpdate);

    void delete(T object);

}
