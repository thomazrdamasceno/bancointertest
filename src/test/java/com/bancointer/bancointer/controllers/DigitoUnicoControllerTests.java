package com.bancointer.bancointer.controllers;
import com.bancointer.bancointer.requestmodel.CalculateDigitRequestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Data
public class DigitoUnicoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions resultResponseCalculateDigit(CalculateDigitRequestObject request) throws Exception {

        return mockMvc.perform(post("/api/digito-unico/calcular-digito/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)));
    }

    @Test
    public void testCalculateDigit() throws Exception {
        String n = "9785";
        int k = 1;
        CalculateDigitRequestObject request = new CalculateDigitRequestObject();
        request.setK(k);
        request.setN(n);
        resultResponseCalculateDigit(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("result")));
    }

    @Test
    public void testCalculateInvalidKDigit() throws Exception {
        String validN = "9785";
        int invalidK = -1;
        CalculateDigitRequestObject request = new CalculateDigitRequestObject();
        request.setK(invalidK);
        request.setN(validN);
        resultResponseCalculateDigit(request)
                 .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateInvalidNDigit() throws Exception {
        String invalidN = "-9785";
        int validK = 1;
        CalculateDigitRequestObject request = new CalculateDigitRequestObject();
        request.setK(validK);
        request.setN(invalidN);
        resultResponseCalculateDigit(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateOtherInvalidNDigit() throws Exception {
        String invalidN = "-9785asda";
        int validK = 1;
        CalculateDigitRequestObject request = new CalculateDigitRequestObject();
        request.setK(validK);
        request.setN(invalidN);
        resultResponseCalculateDigit(request)
                .andExpect(status().isBadRequest());
    }
}
