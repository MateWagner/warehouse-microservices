package com.codecool.order_payment.service;


import com.codecool.order_payment.modell.Postcode;
import com.codecool.order_payment.repository.PostcodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostcodeServiceTest {

    @Mock
    private PostcodeRepository postcodeRepository;

    @InjectMocks
    private PostcodeService postcodeService;

    @Test
    void willFindExistingPostcode() {
        Integer POSTCODE = 3000;
        Postcode postcode = Postcode.builder().postcode(POSTCODE).build();
        when(postcodeRepository.findByPostcode(POSTCODE)).thenReturn(Optional.of(postcode));
        Postcode result = postcodeService.createAndOrGetPostcode(POSTCODE);

        assertEquals(POSTCODE, result.getPostcode());

        verify(postcodeRepository, times(1)).findByPostcode(POSTCODE);
    }

    @Test
    void willSaveNewPostcode() {
        Integer POSTCODE = 3000;
        Postcode postcode = Postcode.builder().postcode(POSTCODE).build();
        when(postcodeRepository.findByPostcode(POSTCODE)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, ""));
        when(postcodeRepository.save(any(Postcode.class))).thenReturn(postcode);
        Postcode result = postcodeService.createAndOrGetPostcode(POSTCODE);

        assertEquals(POSTCODE, result.getPostcode());

        verify(postcodeRepository, times(1)).findByPostcode(POSTCODE);
        verify(postcodeRepository, times(1)).save(any(Postcode.class));
    }

}
