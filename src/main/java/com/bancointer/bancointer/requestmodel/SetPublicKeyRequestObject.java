package com.bancointer.bancointer.requestmodel;

import com.bancointer.bancointer.validators.IsValidPublicKey;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SetPublicKeyRequestObject {

    @IsValidPublicKey
    private String chave;

    @NotNull(message = "{notNullUserId}")
    private Long idUsuario;
}
