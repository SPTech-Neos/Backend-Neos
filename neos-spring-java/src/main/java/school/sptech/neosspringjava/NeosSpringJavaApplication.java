package school.sptech.neosspringjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class NeosSpringJavaApplication {
	public static void main(String[] args) {


		Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DB_HOST_DEV", dotenv.get("DB_HOST_DEV"));
        System.setProperty("DB_PORT_DEV", dotenv.get("DB_PORT_DEV"));
		SpringApplication.run(NeosSpringJavaApplication.class, args);
	}


}