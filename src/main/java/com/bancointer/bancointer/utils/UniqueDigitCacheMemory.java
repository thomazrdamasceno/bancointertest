package com.bancointer.bancointer.utils;

import com.bancointer.bancointer.model.UniqueDigitMapKeys;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public  class UniqueDigitCacheMemory {

    private static Map<UniqueDigitMapKeys, Integer> cacheMap=  new LinkedHashMap<>();

    //Garantir a não-instanciação da classe
    private UniqueDigitCacheMemory(){

    }

    public static void  saveResult(UniqueDigitMapKeys keys, Integer result){

       if(getTotalItems() >= 10) {

           //Remove o primeiro elemento
           UniqueDigitMapKeys fisrtElement =   new ArrayList<>(cacheMap.keySet()).get(0);
           cacheMap.remove(fisrtElement);
       }
       cacheMap.put(keys, result);
    }
    public static Integer getResult(UniqueDigitMapKeys keys){

        return cacheMap.get(keys);
    }

    public static int getTotalItems(){

        return cacheMap.size();
    }
}
