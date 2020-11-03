package com.bancointer.bancointer.security;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface ICryptography {
    KeyPair buildKeyPair();
    String encrypt(String publicKey, String message);
    String decrypt(String privateKey, String text);
    PrivateKey getPrivateKey(String privateKey);
    PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException;
    String getPublicKeyString(PublicKey publicKey);
    String getPrivateKeyString(PrivateKey privateKey);
    Map<String, String> getKeyPairMap();
}

