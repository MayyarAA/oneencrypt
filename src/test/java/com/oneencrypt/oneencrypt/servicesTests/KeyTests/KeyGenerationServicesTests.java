package com.oneencrypt.oneencrypt.servicesTests.KeyTests;

import com.oneencrypt.oneencrypt.central.services.keyservices.KeyGenerationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

@SpringBootTest
public class KeyGenerationServicesTests {

    @Test
    public void checkIfKeyGenerationCreatesKey(){
        SecretKey key  = KeyGenerationService.generateKeyV1();
        Assertions.assertTrue(key instanceof SecretKey);
    }
    @Test
    public void checkIfKeyGenerationCreatesKeyNotNull(){
        SecretKey key  = KeyGenerationService.generateKeyV1();
        Assertions.assertNotNull(key);
    }
}
