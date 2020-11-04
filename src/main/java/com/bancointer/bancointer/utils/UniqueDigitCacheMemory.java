package com.bancointer.bancointer.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public  class UniqueDigitCacheMemory {

    private static Map<UniqueDigitMapKeys, Integer> UNIQUE_DIGIT_CACHE_MAP=  new LinkedHashMap<>();

    //Garantir a não-instanciação da classe
    private UniqueDigitCacheMemory(){}

    public static void  saveResult(UniqueDigitMapKeys keys, Integer result){
        if(getTotalItems() == 10) {
           UniqueDigitMapKeys fisrtElement = new ArrayList<>(UNIQUE_DIGIT_CACHE_MAP.keySet()).get(0);
           UNIQUE_DIGIT_CACHE_MAP.remove(fisrtElement);
        }
        UNIQUE_DIGIT_CACHE_MAP.put(keys, result);
    }
    public static Integer getResult(UniqueDigitMapKeys keys){
        return UNIQUE_DIGIT_CACHE_MAP.get(keys);
    }

    public static int getTotalItems(){
        return UNIQUE_DIGIT_CACHE_MAP.size();
    }
}
