package com.cadolop.timescalculatoremployee;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The class to Servlet Initializer
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * This is method to configure the Servlet initializer 
	 * 
	 * @param application to return the sources.
	 * @return the application builder.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CalculatorSvcApplication.class);
	}

}