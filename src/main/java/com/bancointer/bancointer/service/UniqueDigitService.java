package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.UniqueDigit;
import com.bancointer.bancointer.domain.User;
import com.bancointer.bancointer.repository.IUniqueDigitRepository;
import com.bancointer.bancointer.repository.IUserRepository;
import com.bancointer.bancointer.requestmodel.CalculateDigitRequestObject;
import com.bancointer.bancointer.utils.UniqueDigitCalculator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Data
public class UniqueDigitService implements IUniqueDigitService {

    private IUniqueDigitRepository uniqueDigitRepository;
    private IUserRepository userRepository;

    @Autowired
    public UniqueDigitService(IUserRepository userRepository, IUniqueDigitRepository uniqueDigitRepository){
        this.uniqueDigitRepository = uniqueDigitRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UniqueDigit calculateDigit(CalculateDigitRequestObject request) {
        UniqueDigit uniqueDigit = new UniqueDigit();
        uniqueDigit.setK(request.getK());
        uniqueDigit.setN(request.getN());
        uniqueDigit.setResult(UniqueDigitCalculator.getUniqueDigit(request.getN(),request.getK()));
        //Se houve usuário associado a requisição, faz a associação dele ao digito
        if(request.getIdUsuario() != null) {
          return this.associateUserToCalculation(request.getIdUsuario(), uniqueDigit);
        }
       return uniqueDigit;
    }

    @Transactional
    public List<UniqueDigit> getAllCalculationsByUserId(Long idUsuario){
       return  uniqueDigitRepository.getAllCalculationsByUser(idUsuario);
    }

    @Transactional
    UniqueDigit associateUserToCalculation(Long idUsuario, UniqueDigit uniqueDigit){
        User user = userRepository.findById(idUsuario)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );
        uniqueDigit.setUser(user);
        return uniqueDigitRepository.save(uniqueDigit);
    }
}
