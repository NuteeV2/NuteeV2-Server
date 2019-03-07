package kr.nutee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import kr.nutee.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class NuteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuteeApplication.class, args);
	}

}

