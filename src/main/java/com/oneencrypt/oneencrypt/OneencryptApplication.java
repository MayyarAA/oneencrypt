package com.oneencrypt.oneencrypt;
import com.oneencrypt.oneencrypt.central.CreateFileNamePathService;
import com.oneencrypt.oneencrypt.central.WriteToFileService;
import com.oneencrypt.oneencrypt.central.CreateFileClass;
import com.oneencrypt.oneencrypt.central.FileObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
@SpringBootApplication
public class OneencryptApplication {

	public static void main(String[] args) {

		SpringApplication.run(OneencryptApplication.class, args);
		CreateFileClass createFileClass = new CreateFileClass();
		createFileClass.run();
	}

}
