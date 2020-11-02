package com.bancointer.bancointer.utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueDigitUtilsTests {

    @Test
    @DisplayName("Cálculo do digito único de acordo com os parâmetros n e k (k>1)")
    void calculoDigitoUnicoQuandoKMaiorQueUm(){
            String n = "9875";
            int k = 4;
            int resultadoEsperado = 8;
            int resultadoObtido = UniqueDigitUtils.getUniqueDigit(n,k);
            assertEquals(resultadoEsperado, resultadoObtido);
    }


}
