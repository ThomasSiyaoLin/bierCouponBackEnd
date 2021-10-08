package com.couponsTest.couponDemo.dao;

import com.couponsTest.couponDemo.couponExceptionHandler.UserNotFoundException;
import com.couponsTest.couponDemo.entity.User;
import com.couponsTest.couponDemo.repository.UserRepository;
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
public class UserDao implements Dao<User> {

    @Autowired
    private UserRepository userRepository;

    public UserDao(UserRepository nutzerRepository) {
        this.userRepository = nutzerRepository;
    }
    @Override
    public Optional<User> get(String id) throws UserNotFoundException {

        Optional<User> toReturn = userRepository.findById(id);
        if(toReturn.isEmpty())
            throw new UserNotFoundException(id);
        return toReturn;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User c) {
        userRepository.save(c);
    }


    public void update(String id, User toUpdate) {
            //Not used yet
    }

    @Override
    public void delete(User object) {
        //not used yet
    }

    public boolean isUserAlreadyInDatabase(String id){
        return userRepository.existsById(id);
    }
}
