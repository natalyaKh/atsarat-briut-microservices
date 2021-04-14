package smilyk.atsarat.children;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChildrenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildrenServiceApplication.class, args);
	}

}
