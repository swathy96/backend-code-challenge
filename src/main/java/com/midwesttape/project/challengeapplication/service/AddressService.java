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
                    "from Address a join User on User.address_id = a.id" +
                    " where user.id = ?",
                new BeanPropertyRowMapper<>(Address.class),
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
