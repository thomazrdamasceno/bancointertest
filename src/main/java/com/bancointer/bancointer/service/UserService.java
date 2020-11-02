package com.bancointer.bancointer.service;

import com.bancointer.bancointer.model.User;
import com.bancointer.bancointer.repository.IUserRepository;
import com.bancointer.bancointer.requestmodel.SetPublicKeyRequestObject;
import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.security.ICryptography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.KeyPair;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private ICryptography cryptographyRSA2048 = new CryptographyRSA2048();

    private IUserRepository userRepository;

    @Autowired
    UserService(IUserRepository userRepository){
        this.userRepository = userRepository;

    }

    public User save(User user){

        if(user.getPublicKey() == null){

            String publicKey = getPublicKeyByUserId(user.getId());

            if(publicKey == null){
                KeyPair keypar = cryptographyRSA2048.buildKeyPair();
                publicKey =  cryptographyRSA2048.getPublicKeyString(keypar.getPublic());
            }

            user.setPublicKey(publicKey);
        }

        user.encryptFields();
        return this.userRepository.save(user);
    }

    public void delete(User user){

        this.userRepository.delete(user);
    }

    @Override
    public Optional<User> findById(Long id){

        Optional<User> optionalUser = this.userRepository.findById(id);
        User user =  optionalUser.isPresent() ? optionalUser.get(): null;

        return  Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public String getPublicKeyByUserId(Long idUser){
       return userRepository.getPublicKey(idUser);
    }

    @Override
    public User setPublicKey(SetPublicKeyRequestObject request) {

        User usuario = userRepository
                .findById(request.getIdUsuario())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );

        usuario.setPublicKey(request.getChave());

        //Uma vez que a chave publica associada ao usuário foi modificada, não há integridade nos dados armazenados
        usuario.clearEncryptedFields();
        return userRepository.save(usuario);
    }

}
