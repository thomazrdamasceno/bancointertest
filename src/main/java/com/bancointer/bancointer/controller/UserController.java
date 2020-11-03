package com.bancointer.bancointer.controller;

import com.bancointer.bancointer.model.User;
import com.bancointer.bancointer.requestmodel.SetPublicKeyRequestObject;
import com.bancointer.bancointer.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Data
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/set-chave-publica")
    @ResponseStatus(HttpStatus.OK)
    public User updatePublicKeyForUser(@Valid @RequestBody SetPublicKeyRequestObject request ){

       return userService.updatePublicKeyForUser(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Lista todos os usuários cadastrados no sistema")
    public List<User> getAll(){

        return userService.findAll();

    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Recupera um usuário de acordo com o id informado")
    public User get(@PathVariable Long id){

        return userService
                .findById(id).orElseThrow(()-> new ResponseStatusException((HttpStatus.NOT_FOUND)));

    }

    @PostMapping
    @ApiOperation("Cadastra um novo usuário no sistema")
    public User save(@Valid @RequestBody User user)  {

        return userService.save(user);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Atualiza os dados de um usuário")
    public User put(@Valid @RequestBody User user){

        return userService.save(user);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deleta um usuário específico")
    public void delete(@PathVariable Long id){

        userService
                 .findById(id)
                 .map(user ->{
                     userService.delete(user);
                     return Void.TYPE;
                 })
                 .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

}
