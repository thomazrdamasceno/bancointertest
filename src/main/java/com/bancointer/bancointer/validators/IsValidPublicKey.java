package com.bancointer.bancointer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValidPublicKeyValidator.class)
@Target( { ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidPublicKey {

    String message() default "{invalidPublicKey}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
