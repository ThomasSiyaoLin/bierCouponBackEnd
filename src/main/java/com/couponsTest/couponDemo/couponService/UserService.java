package com.couponsTest.couponDemo.couponService;

import com.couponsTest.couponDemo.dao.UserDao;
import com.couponsTest.couponDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * User Service class acts as an interface to perform basic CRUD operations
 * @author Thomas.Lin
 */
@Service
public class UserService {

    @Autowired
    private final UserDao userDao;

    public UserService(UserDao nutzerDao){
        this.userDao = nutzerDao;
    }




    public User getUser(String id){
        return userDao.get(id).get();
    }


    public User createUser(String firstName, String lastName){

        String newUserId = generateUUID();
        User newUser = new User(newUserId,firstName, lastName);
        userDao.save(newUser);
        return newUser;
    }

    public User createUser(String email){
        String newUserId = email;
        User newUser = new User(newUserId, "NULL", "NULL");
        userDao.save(newUser);
        return newUser;
    }

    public User changeName (String id, String newFirstName, String newLastName){
        User toChange = userDao.get(id).get();
        toChange.setFirstName(newFirstName);
        toChange.setLastName(newLastName);
        userDao.save(toChange);
        return toChange;
    }

    private String generateUUID(){
        String uuid = UUID.randomUUID().toString();
        while(userDao.isUserAlreadyInDatabase(uuid))
            uuid = generateUUID();
        return uuid;
    }


}
