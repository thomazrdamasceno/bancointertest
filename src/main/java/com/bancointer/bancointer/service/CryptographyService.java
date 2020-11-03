package com.bancointer.bancointer.service;

import com.bancointer.bancointer.security.CryptographyRSA2048;
import com.bancointer.bancointer.security.ICryptography;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CryptographyService implements ICryptographyService {

    private final ICryptography cryptography = new CryptographyRSA2048();

    @Override
    public Map<String, String> generateKeyPair() {
        return  cryptography.getKeyPairMap();
    }
}
