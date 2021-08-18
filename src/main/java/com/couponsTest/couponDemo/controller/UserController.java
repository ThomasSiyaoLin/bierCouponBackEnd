package com.couponsTest.couponDemo.controller;


import com.couponsTest.couponDemo.couponExceptionHandler.UserNotFoundException;
import com.couponsTest.couponDemo.couponService.UserService;
import com.couponsTest.couponDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller acts as an REST-API for Users who want to participate in the raffle
 * It provides the following functionalities
 * 1.) Create a new user
 * 2.) Retrieve an existing user
 * 3.) Change the first and last Name of a user
 */

@RestController
@RequestMapping("/UserService")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Retrieve an existing user from the database with a UUID
     * @param userId the UUID which
     * @return If the searched user exist, the corresponding user will be returned as a user object
     * @throws UserNotFoundException if user doesn't exist
     */
    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    /**
     * Create a new user with his first and last name
     * Note that register date and the id if the user will be automatically generated
     * @param firstName
     * @param lastName
     * @return If creating new user i a success, the newly created user will be returned as a user object
     */
    @PutMapping(path ="{firstName}/{lastName}")
    public User createNewUser(@PathVariable("firstName")String firstName, @PathVariable("lastName")String lastName){
        return userService.createUser(firstName, lastName);
    }

    /**
     * Change the last and the first name of a user.
     * @param id The UUID to retrieve the user from the database
     * @param newFirstName The new first name wich will replace the old first name value
     * @param newLastName The new last name wich will replace the old last name value
     * @return if changing was a success the patched user will be returned
     * @throws UserNotFoundException
     */
    @PatchMapping(path = "{id}/{newFirstName}/{newLastName}")
    public User changeUserName(@PathVariable("id") String id, @PathVariable("newFirstName") String newFirstName, @PathVariable("newLastName") String newLastName){
        return userService.changeName(id, newFirstName, newLastName);
    }

}
