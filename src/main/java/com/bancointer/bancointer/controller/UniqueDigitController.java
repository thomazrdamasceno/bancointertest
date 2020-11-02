package com.bancointer.bancointer.controller;

import com.bancointer.bancointer.model.UniqueDigit;
import com.bancointer.bancointer.requestmodel.CalculateDigitRequestObject;
import com.bancointer.bancointer.service.UniqueDigitService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/digito-unico")
@Data
public class UniqueDigitController {

    @Autowired
    private UniqueDigitService uniqueDigitService;

    @PostMapping(path="/calcular-digito/")
    public UniqueDigit calculateUniqueDigit(@Valid @RequestBody CalculateDigitRequestObject request){

        return uniqueDigitService.calculateDigit(request);
    }

    @GetMapping(path="/listar-calculos/{idUsuario}")
    public List<UniqueDigit> listUniqueDigits(@PathVariable Long idUsuario){

        return this.uniqueDigitService.getAllCalculationsByUserId(idUsuario);
    }
}
