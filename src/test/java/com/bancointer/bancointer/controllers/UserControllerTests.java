package com.bancointer.bancointer.controllers;
import com.bancointer.bancointer.domain.User;
import com.bancointer.bancointer.dto.SetPublicKeyRequestDTO;
import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.security.ICryptography;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Data
public class UserControllerTests {

    private ICryptography criptografia = new CryptographyRSA2048();
    private static final String BASE_PATH = "/api/usuario";
    private static final String NAME = "Thomaz Reis Damasceno";
    private static final String EMAIL = "thomazrdamasceno@gmail.com";
    private static final String INVALID_EMAIL = "INVALID_EMAIL";
    private static final String CONTENT_TYPE = "application/json";
    private static final String INVALID_KEY = "INVALID_KEY";
    private static final long ID_USER  = 1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveUser() throws Exception {
        User user = getValidUser();
        mockMvc.perform(post(BASE_PATH)
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveInvalidUser() throws Exception {
        User user = getInvalidUser();
        mockMvc.perform(post(BASE_PATH)
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("A chave publica deve ser setada no objeto usuario")
    void testSetValidPublicKey() throws Exception {
        User user = getValidUser();
        SetPublicKeyRequestDTO requestObject = new SetPublicKeyRequestDTO();
        requestObject.setIdUser(ID_USER);
        requestObject.setKey(user.getPublicKey());
        ResultActions response = mockMvc.perform(post(BASE_PATH+"/set-chave-publica")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(requestObject)));

        response.andExpect(status().isOk())
                .andExpect(content().string(containsString(user.getPublicKey())));
    }

    @Test
    void testSetInvalidPublicKey() throws Exception {
        SetPublicKeyRequestDTO requestObject = new SetPublicKeyRequestDTO();
        requestObject.setIdUser(ID_USER);
        requestObject.setKey(INVALID_KEY);

        ResultActions response = mockMvc.perform(post(BASE_PATH+"/set-chave-publica")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(requestObject)));

        response.andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllUsers() throws Exception {
        ResultActions response = mockMvc.perform(get(BASE_PATH));
        response.andExpect(status().isOk());
    }

    private User getValidUser(){
        User usuario = new User();
        usuario.setEmail(EMAIL);
        usuario.setName(NAME);
        usuario.setPublicKey(criptografia.getPublicKeyString(criptografia.buildKeyPair().getPublic()));
        return usuario;
    }

    private User getInvalidUser(){
        User usuario = new User();
        usuario.setEmail(INVALID_EMAIL);
        usuario.setName("");
        usuario.setPublicKey(criptografia.getPublicKeyString(criptografia.buildKeyPair().getPublic()));
        return usuario;
    }
}
