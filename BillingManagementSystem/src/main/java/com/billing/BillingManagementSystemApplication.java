package com.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BillingManagementSystemApplication extends SpringBootServletInitializer{

	//@Autowired
	//private static ServiceMasterRepository serviceMasterRepository;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BillingManagementSystemApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BillingManagementSystemApplication.class, args);
		
	}

}
