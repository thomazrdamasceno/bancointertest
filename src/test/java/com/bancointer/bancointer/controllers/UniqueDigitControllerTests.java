package com.bancointer.bancointer.controllers;
import com.bancointer.bancointer.dto.CalculateDigitRequestDTO;
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
public class UniqueDigitControllerTests {

    private static final int INVALID_CONCATENATION  = -1;
    private static final int VALID_CONCATENATION  = 1;
    private static final String INVALID_NUMBER  = "-12312";
    private static final String VALID_NUMBER  = "9785";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ResultActions resultResponseCalculateDigit(CalculateDigitRequestDTO request) throws Exception {
        return mockMvc.perform(post("/api/digito-unico/calcular-digito/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)));
    }

    @Test
    public void testCalculateDigit() throws Exception {
        CalculateDigitRequestDTO request = new CalculateDigitRequestDTO();
        request.setConcatenation(VALID_CONCATENATION);
        request.setNumber(VALID_NUMBER);
        resultResponseCalculateDigit(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("result")));
    }

    @Test
    public void testCalculateInvalidConcatenation() throws Exception {
        CalculateDigitRequestDTO request = new CalculateDigitRequestDTO();
        request.setConcatenation(INVALID_CONCATENATION);
        request.setNumber(VALID_NUMBER);
        resultResponseCalculateDigit(request)
                 .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateInvalidNumber() throws Exception {
        CalculateDigitRequestDTO request = new CalculateDigitRequestDTO();
        request.setConcatenation(VALID_CONCATENATION);
        request.setNumber(INVALID_NUMBER);
        resultResponseCalculateDigit(request)
                .andExpect(status().isBadRequest());
    }

}
