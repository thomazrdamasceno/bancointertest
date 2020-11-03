package com.bancointer.bancointer.dto;

import com.bancointer.bancointer.validators.IsValidPublicKey;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SetPublicKeyRequestDTO {

    @IsValidPublicKey
    private String key;

    @NotNull(message = "{notNullUserId}")
    private Long idUser;
}
