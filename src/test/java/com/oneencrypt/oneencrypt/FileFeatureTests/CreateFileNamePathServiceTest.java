package com.oneencrypt.oneencrypt.FileFeatureTests;

import com.oneencrypt.oneencrypt.central.FileFeature.CreateFileNamePathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateFileNamePathServiceTest {

    @BeforeEach
    public void setUp(){
    }
    @Test
    public void shouldCreateFilePathNameWithOutFileTag(){
        String filePathName = CreateFileNamePathService.createFilePathName("filename","filepath/");
        Assertions.assertTrue((filePathName.contains("filename") &&filePathName.contains("filepath/")  ));
    }

    @Test
    public void shouldCreateRandomNumber(){
        int ranNum = CreateFileNamePathService.generateRandomFileTage();
        Assertions.assertNotNull(ranNum);
    }
    @Test
    public void shouldCreateFilePathNameWithFileTag(){
        String filePathName = CreateFileNamePathService.createFilePathName("filename","filepath/",455);
        Assertions.assertTrue((filePathName.contains("filename") &&filePathName.contains("filepath/")&&filePathName.contains("455")  ));
    }
}
