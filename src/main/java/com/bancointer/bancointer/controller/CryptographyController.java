package com.bancointer.bancointer.controller;
import com.bancointer.bancointer.service.ICryptographyService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/criptografia")
@Data
@ApiOperation("Criptografia")
public class CryptographyController {

    private ICryptographyService cryptographyService;

    @Autowired
    public CryptographyController(ICryptographyService cryptographyService){
        this.cryptographyService = cryptographyService;
    }

    @GetMapping(path="/gerar-key-pair")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Gera um par de chaves (publica e privada) para ser utilizada no processo de criptografia/descriptografia")
    public Map<String, String> generateKeyPair(){

       return cryptographyService.generateKeyPair();
    }
}
