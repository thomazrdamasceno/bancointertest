package com.bancointer.bancointer.controllers;
import com.bancointer.bancointer.model.User;
import com.bancointer.bancointer.requestmodel.SetPublicKeyRequestObject;
import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.security.ICryptography;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private static final String CONTENT_TYPE = "application/json";

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

        SetPublicKeyRequestObject requestObject = new  SetPublicKeyRequestObject();
        requestObject.setIdUsuario(Long.valueOf(1));
        requestObject.setChave(user.getPublicKey());

        ResultActions response = mockMvc.perform(post(BASE_PATH+"/set-chave-publica")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(requestObject)));

        response.andExpect(status().isOk())
                .andExpect(content().string(containsString(user.getPublicKey())));

    }

    @Test
    void testGetAllUsers() throws Exception {

        ResultActions response = mockMvc.perform(get(BASE_PATH));
        response.andExpect(status().isOk());

    }

    @Test
    void testSetInvalidPublicKey() throws Exception {

        User usuario = getValidUser();

        SetPublicKeyRequestObject requestObject = new  SetPublicKeyRequestObject();
        requestObject.setIdUsuario(Long.valueOf(1));
        requestObject.setChave("invalidKey");

        ResultActions response = mockMvc.perform(post(BASE_PATH+"/set-chave-publica")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(requestObject)));

        response.andExpect(status().isBadRequest());


    }

    private User getValidUser(){
        User usuario = new User();
        usuario.setEmail("thomazrdamasceno@gmail.com");
        usuario.setNome("Thomaz Reis Damasceno");
        usuario.setPublicKey(criptografia.getPublicKeyString(criptografia.buildKeyPair().getPublic()));
        return usuario;
    }


    private User getInvalidUser(){
        User usuario = new User();
        usuario.setEmail("mail");
        usuario.setNome("");
        usuario.setPublicKey(criptografia.getPublicKeyString(criptografia.buildKeyPair().getPublic()));
        return usuario;
    }
}
