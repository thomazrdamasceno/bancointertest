package com.bancointer.bancointer.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.security.KeyPair;

public class CryptographyRSA2048Tests {

    private ICryptography cryptographyRSA2048= new CryptographyRSA2048();

    @Test
    void testEncryptAndDecrypt()  {

        KeyPair key = this.cryptographyRSA2048.buildKeyPair();
        String nomeOriginal = "Thomaz";
        String nomeCriptografado = this.cryptographyRSA2048.encrypt(this.cryptographyRSA2048.getPublicKeyString(key.getPublic()), nomeOriginal);
        String nomeDesCriptografado = this.cryptographyRSA2048.decrypt(this.cryptographyRSA2048.getPrivateKeyString(key.getPrivate()), nomeCriptografado);
        assertEquals(nomeOriginal,nomeDesCriptografado);
    }
}
