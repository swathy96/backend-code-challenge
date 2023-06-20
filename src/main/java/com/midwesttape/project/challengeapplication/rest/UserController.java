package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/vq1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PutMapping("v1/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable final Long userId, @RequestBody User user){
        boolean isUpdated = userService.updateUser(userId, user);
        if(isUpdated)
            return new ResponseEntity<User>(userService.user(userId),HttpStatus.OK);//Returning the updated user details
        return new ResponseEntity<User>(user,HttpStatus.NOT_MODIFIED);// Returning Not modified
    }



}
