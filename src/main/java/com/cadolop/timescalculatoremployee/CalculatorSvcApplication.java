package com.cadolop.timescalculatoremployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class to startup the application
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@SpringBootApplication
public class CalculatorSvcApplication {

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalculatorSvcApplication.class, args);
	}
}