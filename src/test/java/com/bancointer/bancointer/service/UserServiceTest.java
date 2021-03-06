package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.User;
import com.bancointer.bancointer.repository.IUserRepository;
import com.bancointer.bancointer.dto.SetPublicKeyRequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private IUserRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(repository);
    }

    @Test
    public void testWhenSaveAUserThePublicKeyMustBeConfigured(){
        User user = getDefaultUser();
        when(repository.save(user)).thenReturn(user);
        User returnedUser = userService.save(user);
        assertNotNull(returnedUser.getPublicKey());
    }

    @Test
    public void testUpdatePublicKeyForUser(){
        SetPublicKeyRequestDTO request = new SetPublicKeyRequestDTO();
        Long idUser = 1L;
        String keyToUpdate = "KEY_TO_UPDATE";
        request.setKey(keyToUpdate);
        request.setIdUser(idUser);
        User userToUpdate = getDefaultUser();
        userToUpdate.setId(idUser);
        when(repository.findById(idUser)).thenReturn(Optional.of( userToUpdate ));
        when(repository.save(userToUpdate)).thenReturn(userToUpdate);
        User returnedUser = userService.updatePublicKeyForUser(request);
        assertEquals(returnedUser.getPublicKey(), keyToUpdate);
    }

    private User getDefaultUser(){
        User user = new User();
        user.setName("Thomaz Reis Damasceno");
        user.setEmail("thomazrdamasceno@gmail.com");
        return user;
    }
}
