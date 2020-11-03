package com.bancointer.bancointer.utils;

import com.bancointer.bancointer.domain.UniqueDigitMapKeys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class UniqueDigitCacheMemoryTests {

    @Test
    @DisplayName("Teste de inclusão e recuperação de itens pelas chaves n e k do cache")
    void testAddAndGetItemsUsingKeys(){
        Integer k = 1;
        String n = "9875";
        Integer result = 25;
        UniqueDigitCacheMemory.saveResult(new UniqueDigitMapKeys(n,k), result);
        Integer resultCache = UniqueDigitCacheMemory.getResult(new UniqueDigitMapKeys(n,k));
        assertEquals(result, resultCache);
    }

    @Test
    @DisplayName("Ao tentar recuperar um conjunto k e n inexistente, o resultado deve ser null")
    void testNonExistentKeys(){
        Integer k = 1;
        String n = "9875";
        Integer result = 25;
        UniqueDigitCacheMemory.saveResult(new UniqueDigitMapKeys(n,k), result);
        Integer resultCache = UniqueDigitCacheMemory.getResult(new UniqueDigitMapKeys("55",2));
        assertNull(resultCache);
    }

    @Test
    @DisplayName("O Cache deve guardar apenas os ultimos 10 regitros")
    void tesSaveOnly10LatestRecords(){
        int countItems = 11;
        IntStream.rangeClosed(1, countItems).mapToObj(i -> new UniqueDigitMapKeys(String.valueOf(i), i)).forEach(keys -> UniqueDigitCacheMemory.saveResult(keys, 25));
        int totalItemsInCache =  UniqueDigitCacheMemory.getTotalItems();
        assertTrue(totalItemsInCache <=10 );
    }

}
