package com.bancointer.bancointer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    Classe auxiliar criada para o mapeamento do cojuntos de chaves (n,k)
    para utilização no mapamento de resultados de cálculos de digitos únicos,
    na classe DigitoUnicoCacheMemory
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniqueDigitMapKeys {

    private String n;
    private Integer k;
}
