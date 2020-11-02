package com.bancointer.bancointer.utils;

import com.bancointer.bancointer.model.UniqueDigitMapKeys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueDigitCacheMemoryTests {

    @Test
    @DisplayName("Teste de inclusão e recuperação de itens pelas chaves n e k do cache")
    void testAddAndGetItensUsingKeys(){

        Integer k = 1;
        String n = "9875";
        Integer result = 25;
        UniqueDigitCacheMemory.saveResult(new UniqueDigitMapKeys(n,k), result);
        Integer resultadoDoCache = UniqueDigitCacheMemory.getResult(new UniqueDigitMapKeys(n,k));
        assertEquals(result, resultadoDoCache);
    }

    @Test
    @DisplayName("Ao tentar recuperar um conjunto k e n inexistente, o resultado deve ser null")
    void testRecuperacaoKeysInexistentes(){

        Integer k = 1;
        String n = "9875";
        Integer result = 25;
        UniqueDigitCacheMemory.saveResult(new UniqueDigitMapKeys(n,k), result);
        Integer resultadoDoCache = UniqueDigitCacheMemory.getResult(new UniqueDigitMapKeys("55",2));
        assertNull(resultadoDoCache);
    }

    @Test
    @DisplayName("O Cache deve guardar apenas os ultimos 10 regitros")
    void deveGuardarApenasOsUltimos10Registros(){

        int quantidadeDeItensParaInserir = 11;
        for(int i = 1; i<=quantidadeDeItensParaInserir; i++){
            UniqueDigitMapKeys keys = new UniqueDigitMapKeys(String.valueOf(i),i);
            UniqueDigitCacheMemory.saveResult(keys, 25);
        }

        int totalDeItensNoCache =  UniqueDigitCacheMemory.getTotalItems();
        assertTrue(totalDeItensNoCache <=10 );
    }
}
