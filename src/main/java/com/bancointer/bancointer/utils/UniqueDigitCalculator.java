package com.bancointer.bancointer.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueDigitCalculator {
    //Garantir a não-instanciação da classe
    private UniqueDigitCalculator(){

    }

    public static int getUniqueDigit(String number, int concatenation) {
        UniqueDigitMapKeys keys =  new UniqueDigitMapKeys(number, concatenation);
        Integer result = UniqueDigitCacheMemory.getResult(keys);
        if(result == null){
           result = calculateUniqueDigit(number, concatenation);
           UniqueDigitCacheMemory.saveResult(keys, result);
        }
        return result;
    }

    private static int calculateUniqueDigit(String number, int concatenation){
        String p = IntStream.rangeClosed(1, concatenation).mapToObj(i -> number).collect(Collectors.joining());
        return recursiveReduceNumber(p);
    }

    private static Integer recursiveReduceNumber(String number){
        if(number.length()==1){
            return Integer.valueOf(number);
        }
        int result =  number.chars()
                .map(Character::getNumericValue)
                .reduce(0, Integer::sum);

        return recursiveReduceNumber(String.valueOf(result));
    }
}
