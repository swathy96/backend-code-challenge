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
    public ResponseEntity<Object> updateUser(@PathVariable final Long userId, @RequestBody User user){
        String  status = userService.updateUser(userId, user);
        if(status.equals("Success"))
            return new ResponseEntity<Object>(userService.user(userId),HttpStatus.OK);//Returning the updated user details
        return new ResponseEntity<Object>(status,HttpStatus.BAD_REQUEST);// Returning Bad Request as something is missing
    }



}
