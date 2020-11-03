package com.bancointer.bancointer.domain;

public interface IUser {

    User encryptFields();
    void clearEncryptedFields();

}
