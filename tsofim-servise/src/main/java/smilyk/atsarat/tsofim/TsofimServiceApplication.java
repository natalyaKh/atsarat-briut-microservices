package smilyk.atsarat.tsofim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard

public class TsofimServiceApplication {

	public static void main(String[] args)
	{
//	    System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		System.setProperty("webdriver.gecko.driver",
				"driver/geckodriver");
		SpringApplication.run(TsofimServiceApplication.class, args);
	}
}
