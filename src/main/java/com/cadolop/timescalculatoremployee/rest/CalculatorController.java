package com.cadolop.timescalculatoremployee.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadolop.timescalculatoremployee.dto.EmployeeDto;
import com.cadolop.timescalculatoremployee.service.EmployeeService;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@RestController
public class CalculatorController {
	private static final Logger LOG = Logger.getLogger(CalculatorController.class.getName());
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@RequestMapping({ "/calculate" })
	public ResponseEntity<Object> calculate(@RequestBody EmployeeDto employeeDto) {
		try {
			List<String> messages = employeeService.validator(employeeDto);
			if (messages.size() > 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
			return ResponseEntity.ok(employeeService.calculate(employeeService.callSoapEmployee(employeeDto)));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
}