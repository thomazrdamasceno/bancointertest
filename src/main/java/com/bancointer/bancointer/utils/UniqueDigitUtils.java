package com.bancointer.bancointer.utils;

import com.bancointer.bancointer.model.UniqueDigit;
import com.bancointer.bancointer.model.UniqueDigitMapKeys;

public class UniqueDigitUtils {

    //Garantir a não-instanciação da classe
    private UniqueDigitUtils(){

    }

    public static int getUniqueDigit(String n, int k) {

       UniqueDigitMapKeys keys =  new UniqueDigitMapKeys(n,k);
       Integer result = UniqueDigitCacheMemory.getResult(keys);
       if(result == null){
           result = calculateUniqueDigit(n, k);
           UniqueDigitCacheMemory.saveResult(keys, result);
       }
       return result;
    }

    private static int calculateUniqueDigit(String n, int k){

        StringBuilder p =  new StringBuilder();

        for(int i=1; i<=k; i++){
            p.append(n);
        }

        return recursiveReduceNumber(p.toString());
    }

    private static Integer recursiveReduceNumber(String p){

        if(p.length()==1){
            return Integer.valueOf(p);
        }
        int result =  p.chars()
                .map(Character::getNumericValue)
                .reduce(0, Integer::sum);

        return recursiveReduceNumber(String.valueOf(result));

    }

}
