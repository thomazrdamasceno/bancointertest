package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.UniqueDigit;
import com.bancointer.bancointer.dto.CalculateDigitRequestDTO;

public interface IUniqueDigitService {
    UniqueDigit calculateDigit(CalculateDigitRequestDTO request);
}
