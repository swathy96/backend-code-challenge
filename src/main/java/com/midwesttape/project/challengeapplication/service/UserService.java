package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;
    private final AddressService addressService;

    public User user(final Long userId) {
        try {
            User user =  template.queryForObject(
                "select " +
                    "id, " +
                    "firstName, " +
                    "lastName, " +
                    "username, " +
                    "password " +
                    "from User " +
                    "where id = ?",
                new BeanPropertyRowMapper<>(User.class),
                userId
            );
            if(user != null){
                user.setAddress(addressService.userAddress(userId));
            }
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public String updateUser(Long userId, User updatedUser){
        try{
           int status = template.update("update User set firstName = ?, lastName = ?, username = ?, password = ? where id = ?",
                updatedUser.getFirstName(),updatedUser.getLastName(),updatedUser.getUsername(),updatedUser.getPassword(),userId);
            return status > 0 && addressService.updateUserAddress(userId, updatedUser.getAddress()) > 0 ? "Success":"Unsuccessful";

        }catch(EmptyResultDataAccessException e){
            return "No User found";//This exception occurs when user is not found
        }
        catch(Exception e){
            e.printStackTrace();
            return "One of the User field is incorrect or missing, please check"; //Unique constraint violation, field missing
        }
    }

}
