package com.bancointer.bancointer.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class CalculateDigitRequestDTO {

    private Long idUsuario;

    @NotEmpty(message = "O valor de n deve ser espefificado")
    @Pattern(regexp = "[0-9]+", message = "O valor definido para variável n é inválido")
    private String n;

    @Min(value = 1,message = "o valor de k deve ser maior ou igual a 1")
    private int k;
}
