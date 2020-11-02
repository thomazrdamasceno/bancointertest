package com.bancointer.bancointer.security;

public class CryptographyRSA2048 extends Cryptography {

    private static final int KEY_SIZE = 2048;
    private static final String ALGORITHM = "RSA";

    public CryptographyRSA2048() {
        super(KEY_SIZE, ALGORITHM);
    }
}

