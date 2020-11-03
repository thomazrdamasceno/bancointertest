package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.UniqueDigit;
import com.bancointer.bancointer.dto.CalculateDigitRequestDTO;

import java.util.List;

public interface IUniqueDigitService {
    UniqueDigit calculateDigit(CalculateDigitRequestDTO request);
    List<UniqueDigit> getAllCalculationsByUserId(Long idUsuario);
    boolean checkIfCalculationExistsForUser(Long idUser, String number, int concatenation);
}
