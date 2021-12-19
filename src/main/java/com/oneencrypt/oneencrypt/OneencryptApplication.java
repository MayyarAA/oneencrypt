package com.oneencrypt.oneencrypt;
import com.oneencrypt.oneencrypt.central.WriteToFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneencryptApplication {

	public static void main(String[] args) {

		SpringApplication.run(OneencryptApplication.class, args);
		new WriteToFileService();
	}

}
