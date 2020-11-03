package com.bancointer.bancointer.controllers;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Data
public class CryptographyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGenerateKeyPair() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/criptografia/gerar-key-pair"));
        response.andExpect(status().isOk())
                .andExpect(content().string(containsString("publicKey")))
                .andExpect(content().string(containsString("privateKey")));

    }
}
