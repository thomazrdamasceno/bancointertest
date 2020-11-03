package com.bancointer.bancointer.service;

import com.bancointer.bancointer.domain.UniqueDigit;
import com.bancointer.bancointer.domain.User;
import com.bancointer.bancointer.repository.IUniqueDigitRepository;
import com.bancointer.bancointer.repository.IUserRepository;
import com.bancointer.bancointer.dto.CalculateDigitRequestDTO;
import com.bancointer.bancointer.utils.UniqueDigitCalculator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public UniqueDigit calculateDigit(CalculateDigitRequestDTO request) {
        UniqueDigit uniqueDigit = new UniqueDigit();
        uniqueDigit.setConcatenation(request.getConcatenation());
        uniqueDigit.setNumber(request.getNumber());
        uniqueDigit.setResult(UniqueDigitCalculator.getUniqueDigit(request.getNumber(),request.getConcatenation()));
        //Se houve usuário associado a requisição, faz a associação dele ao digito
        if(request.getIdUser() != null) {
          return this.associateUserToCalculation(request.getIdUser(), uniqueDigit);
        }
       return uniqueDigit;
    }

    @Transactional
    @Override
    public List<UniqueDigit> getAllCalculationsByUserId(Long idUsuario){
       return  uniqueDigitRepository.getAllCalculationsByUser(idUsuario);
    }

    @Transactional
    @Override
    public boolean checkIfCalculationExistsForUser(Long idUser, String number, int concatenation){
        return  uniqueDigitRepository.checkIfCalculationExistsForUser(idUser, number,concatenation);
    }

    @Transactional
    UniqueDigit associateUserToCalculation(Long idUsuario, UniqueDigit uniqueDigit){

        //Verifica se já existe um cálculo com os mesmos parâmetros já salvo para o usuário em questão
        if( checkIfCalculationExistsForUser(idUsuario, uniqueDigit.getNumber(), uniqueDigit.getConcatenation()) ) {
            return uniqueDigit;
        }
        User user = userRepository.findById(idUsuario)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );
        uniqueDigit.setUser(user);
        return uniqueDigitRepository.save(uniqueDigit);
    }
}
