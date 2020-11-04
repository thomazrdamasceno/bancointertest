package com.bancointer.bancointer.utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueDigitCalculatorTests {

    @Test
    @DisplayName("Cálculo do digito único de acordo com os parâmetros n e k (k>1)")
    void calculateTests(){
        assertEquals(UniqueDigitCalculator.getUniqueDigit("9875",4), 8);
        assertEquals(UniqueDigitCalculator.getUniqueDigit("9875",1), 2);
        assertEquals(UniqueDigitCalculator.getUniqueDigit("15000",1), 6);
        assertEquals(UniqueDigitCalculator.getUniqueDigit("900",2), 9);
        assertEquals(UniqueDigitCalculator.getUniqueDigit("900",2), 9);
    }



}
