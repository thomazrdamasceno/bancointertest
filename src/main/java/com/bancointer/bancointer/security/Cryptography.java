package com.bancointer.bancointer.security;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cryptography implements ICryptography {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    static Logger logger =  LoggerFactory.getLogger(Cryptography.class);

    private int keySize;
    private String algorithm;

    public Cryptography(int keySize, String algorithm){
        this.keySize = keySize;
        this.algorithm = algorithm;
    }

    @Override
    public  KeyPair buildKeyPair() {

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(this.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Erro ao gerar keypar", e);
        }
        assert keyPairGenerator != null;
        keyPairGenerator.initialize(this.getKeySize());
        return keyPairGenerator.genKeyPair();

    }
    @Override
    public  String getPublicKeyString(PublicKey publicKey){

        return encoder.encodeToString(publicKey.getEncoded());
    }

    @Override
    public String getPrivateKeyString(PrivateKey privateKey){

        return encoder.encodeToString(privateKey.getEncoded());
    }

    @Override
    public String encrypt(String  publicKeyString, String message)  {

       if(message == null || message.length() == 0){
           return "";
       }

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(this.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, this.getPublicKey(publicKeyString));
            return encoder.encodeToString(cipher.doFinal(message.getBytes()));
        } catch (Exception e) {
            logger.error("Erro ao criptografar String", e);
            return null;
        }

    }
    @Override
    public String decrypt(String privateKeyString, String text)  {

        try{
            Cipher cipher = Cipher.getInstance(this.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, this.getPrivateKey(privateKeyString));

            return new String(cipher.doFinal(decoder.decode(text)));
        }catch(Exception e){
            logger.error("Erro ao descriptografar String", e);
            return null;
        }

    }
    @Override
    public PrivateKey getPrivateKey(String privateKey) {

        try{

            byte[] keyBytes = decoder.decode(privateKey.getBytes());

            PKCS8EncodedKeySpec spec =
                    new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(this.getAlgorithm());
            return kf.generatePrivate(spec);

        }catch(Exception e){
            logger.error("Erro ao gerar private key", e);
            return null;
        }

    }
    @Override
    public  PublicKey getPublicKey(String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {


            byte[] keyBytes = decoder.decode(publicKey.getBytes());

            X509EncodedKeySpec spec =
                    new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(this.getAlgorithm());
            return kf.generatePublic(spec);

    }

    @Override
    public Map<String, String> getKeyPairMap(){
        KeyPair keypair = this.buildKeyPair();
        Map<String, String> map = new HashMap<>();
        String privateKey = getPrivateKeyString(keypair.getPrivate());
        String publicKey = getPublicKeyString(keypair.getPublic());
        map.put("privateKey", getPrivateKeyString(keypair.getPrivate()));
        map.put("publicKey", getPublicKeyString(keypair.getPublic()));
        logger.info("privateKey gerada: "+privateKey);
        logger.info("publicKey gerada: "+publicKey);
        return map;
    }
}
