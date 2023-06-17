package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    private static final Long USER_ID = 1234L;

    @Mock
    private JdbcTemplate template;
    private AddressService addressService;

    @BeforeEach
    public void beforeEach() {
        addressService = new AddressService(template);
    }

    @Test
    public void should_get_user_address(){
        final Address address = new Address();
        when(template.queryForObject(anyString(),isA(BeanPropertyRowMapper.class), eq(USER_ID))).thenReturn(address);
        final Address resultAddress = addressService.userAddress(USER_ID);
        assertEquals(address,resultAddress);
    }

}
