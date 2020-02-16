package com.cadolop.timescalculatoremployee;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CalculatorSvcApplication.class);
	}

}