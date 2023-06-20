package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final JdbcTemplate template;

    public Address userAddress(final Long userId) { //Since the address is retrieved based on the userId
        try {

            return template.queryForObject(
                "select " +
                    "a.id, " +
                    "address1, " +
                    "address2, " +
                    "city, " +
                    "state, " +
                    "postal " +
                    "from Address a join User on User.addressId = a.id" +
                    " where user.id = ?",
                new BeanPropertyRowMapper<>(Address.class),
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //h2 doesn't support join hence the usage of nested query
    public int updateUserAddress(final Long userId, Address updatedAddress){
        return template.update(
            "UPDATE Address " +
                "SET address1 = ?, address2 = ?, city = ?, state = ?, postal = ? " +
                "WHERE id = (SELECT addressId FROM User WHERE id = ?)", updatedAddress.getAddress1(),
            updatedAddress.getAddress2(),updatedAddress.getCity(),updatedAddress.getState(),updatedAddress.getPostal(),userId);

    }
}
