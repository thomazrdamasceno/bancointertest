package com.bancointer.bancointer.service;

import com.bancointer.bancointer.model.UniqueDigit;
import com.bancointer.bancointer.requestmodel.CalculateDigitRequestObject;

public interface IUniqueDigitService {

    UniqueDigit calculateDigit(CalculateDigitRequestObject request);

}