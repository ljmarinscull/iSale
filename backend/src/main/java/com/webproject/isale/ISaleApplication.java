package com.webproject.isale;

import com.webproject.isale.utils.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ISaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ISaleApplication.class, args);
	}

}
