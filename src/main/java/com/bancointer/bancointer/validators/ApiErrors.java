package com.bancointer.bancointer.validators;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }
}