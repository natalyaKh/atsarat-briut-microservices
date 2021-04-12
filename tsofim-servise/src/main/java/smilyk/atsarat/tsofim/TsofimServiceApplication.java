package smilyk.atsarat.tsofim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@SpringBootApplication
//@EnableCircuitBreaker
//@EnableHystrix
//@EnableHystrixDashboard

public class TsofimServiceApplication {

	public static void main(String[] args)
	{
		System.setProperty("webdriver.gecko.driver",
				"driver/geckodriver");
		SpringApplication.run(TsofimServiceApplication.class, args);
	}
}
