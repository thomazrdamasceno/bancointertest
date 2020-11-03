package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.UniqueDigit;
import com.bancointer.bancointer.repository.IUniqueDigitRepository;
import com.bancointer.bancointer.dto.CalculateDigitRequestDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UniqueDigitServiceTest {

    private UniqueDigitService uniqueDigitService;
    private IUniqueDigitRepository uniqueDigitRepository;

    @Before
    public void init(){
        uniqueDigitRepository = mock(IUniqueDigitRepository.class);
        uniqueDigitService = new UniqueDigitService(null,uniqueDigitRepository);
    }

    @Test
    public void testCalculateDigit(){
        CalculateDigitRequestDTO request = new CalculateDigitRequestDTO();
        request.setConcatenation(1);
        request.setNumber("9875");
        UniqueDigit digitReturned = uniqueDigitService.calculateDigit(request);
        assertEquals(digitReturned.getResult(),2);
    }

}
