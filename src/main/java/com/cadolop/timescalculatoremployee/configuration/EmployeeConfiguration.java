package com.cadolop.timescalculatoremployee.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.cadolop.timescalculatoremployee.soapclient.EmployeeClient;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@Configuration
public class EmployeeConfiguration {

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("io.spring.guides.gs_producing_web_service");
		return marshaller;
	}

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Bean
	public EmployeeClient employeeClient(Jaxb2Marshaller marshaller) {
		EmployeeClient client = new EmployeeClient();
		client.setDefaultUri("http://localhost:18080/calculator-svc/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
