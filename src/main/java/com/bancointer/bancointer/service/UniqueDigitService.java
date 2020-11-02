package com.bancointer.bancointer.service;

import com.bancointer.bancointer.model.UniqueDigit;
import com.bancointer.bancointer.model.User;
import com.bancointer.bancointer.repository.UniqueDigitRepository;
import com.bancointer.bancointer.repository.IUserRepository;
import com.bancointer.bancointer.requestmodel.CalculateDigitRequestObject;
import com.bancointer.bancointer.utils.UniqueDigitUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Data
public class UniqueDigitService implements IUniqueDigitService {

    @Autowired
    private UniqueDigitRepository uniqueDigitRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UniqueDigit calculateDigit(CalculateDigitRequestObject request) {

        UniqueDigit uniqueDigit = new UniqueDigit();
        uniqueDigit.setK(request.getK());
        uniqueDigit.setN(request.getN());
        uniqueDigit.setResult(UniqueDigitUtils.getUniqueDigit(request.getN(),request.getK()));

        //Se houve usuário associado a requisição, faz a associação dele ao digito
        if(request.getIdUsuario()!=null) {
          return this.associateUserToCalculation(request.getIdUsuario(), uniqueDigit);
        }

       return uniqueDigit;
    }

    public List<UniqueDigit> getAllCalculationsByUserId(Long idUsuario){
       return  uniqueDigitRepository.getAllCalculationsByUser(idUsuario);
    }

    private UniqueDigit associateUserToCalculation(Long idUsuario, UniqueDigit uniqueDigit){

            User user = userRepository.findById(idUsuario)
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND) );
            uniqueDigit.setUser(user);
            return uniqueDigitRepository.save(uniqueDigit);

    }
}
