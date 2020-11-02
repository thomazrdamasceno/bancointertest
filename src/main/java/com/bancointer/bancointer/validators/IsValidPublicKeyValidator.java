package com.bancointer.bancointer.validators;

import com.bancointer.bancointer.model.User;
import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.security.ICryptography;
import com.bancointer.bancointer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class IsValidPublicKeyValidator implements ConstraintValidator<IsValidPublicKey, Object> {

    private ICryptography cryptography= new CryptographyRSA2048();

    @Autowired
    private IUserService userService;

    public IsValidPublicKeyValidator(){

    }

    @Override
    public void initialize(IsValidPublicKey constraint) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        String publicKeyString = "";

        if(object instanceof User){
            User user = (User) object;
            publicKeyString = user.getPublicKey();

            if(publicKeyString == null && user.getId()!=0){
                publicKeyString = userService.getPublicKeyByUserId(user.getId());

            }
        }
        else if(object instanceof String){
            publicKeyString  = (String) object;
        }

      try{
          return cryptography.getPublicKey(publicKeyString).getAlgorithm().equals("RSA");
      }catch(Exception e){
            return false;
      }

    }
}