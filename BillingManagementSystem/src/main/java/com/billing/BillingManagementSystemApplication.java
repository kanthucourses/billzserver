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
		/*
		ServiceMaster service = new ServiceMaster();
		try {
			ServiceMaster serviceMasterDbObj =serviceMasterRepository.findByServiceID("2001");
			if(serviceMasterDbObj != null) {
				BigDecimal height = serviceMasterDbObj.getHeight();
				BigDecimal length = serviceMasterDbObj.getLength();
				BigDecimal area = height.multiply(length);

				System.out.println("height"+height);
				System.out.println("length"+length);
				System.out.println("area"+area);


			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
	}

}
