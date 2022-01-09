package com.oneencrypt.oneencrypt.servicesTests.encryptionTests;

import com.oneencrypt.oneencrypt.central.services.encryption.EncryptionService;
import com.oneencrypt.oneencrypt.central.services.keyservices.KeyGenerationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
@SpringBootTest
public class EncryptionServiceTests {
    EncryptionService encryptionService;
    SecretKey encryptionKey;
    @BeforeEach
    public void setup(){
        this.encryptionKey = KeyGenerationService.generateKeyV1();
        this.encryptionService = new EncryptionService(encryptionKey);
    }

    @Test
    public void checkIfCanEncryptString(){
        String unEncryptedString = "String1Unencrypted";
        String encryptedString = encryptionService.encryptString(unEncryptedString);
        Assertions.assertNotEquals(unEncryptedString,encryptedString);
    }

    @Test
    public void checkIfCanEncryptAndDecryptString(){
        String unEncryptedString = "String1Unencrypted";
        String encryptedString = encryptionService.encryptString(unEncryptedString);
        String decryptedString = encryptionService.decryptString(encryptedString);
        Assertions.assertEquals(unEncryptedString,decryptedString);
    }

}
