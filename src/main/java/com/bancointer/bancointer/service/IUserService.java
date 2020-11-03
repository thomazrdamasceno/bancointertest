package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.User;
import com.bancointer.bancointer.dto.SetPublicKeyRequestDTO;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);
    void delete(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    String getPublicKeyByUserId(Long idUser);
    User updatePublicKeyForUser(SetPublicKeyRequestDTO request);
}
