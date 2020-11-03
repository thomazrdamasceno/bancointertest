package com.bancointer.bancointer.utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueDigitUtilsTests {

    @Test
    @DisplayName("Cálculo do digito único de acordo com os parâmetros n e k (k>1)")
    void calculateUniqueDigitWhenKGreaterThanOne(){
            String n = "9875";
            int k = 4;
            int expectedResult= 8;
            int result = UniqueDigitUtils.getUniqueDigit(n,k);
            assertEquals(expectedResult, result);
    }


}
