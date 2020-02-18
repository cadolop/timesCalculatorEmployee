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
import com.cadolop.timescalculatoremployee.model.Employee;
import com.cadolop.timescalculatoremployee.service.EmployeeService;

/**
 * The class rest controller employee
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
	 * This method define the endpoint of the rest controller
	 * 
	 * @param employeeDto the data transfer object front level.
	 * @return the response entity with the dates calculate in year, month and days.
	 */
	@RequestMapping({ "/calculate" })
	public ResponseEntity<Object> calculate(@RequestBody EmployeeDto employeeDto) {
		try {
			List<String> messages = employeeService.validator(employeeDto);
			if (messages.size() > 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
			Employee employee;
			try {
				employee = employeeService.callSoapEmployee(employeeDto);
				if (employee != null && employee.getId() > 0L)
					messages.add("Saved id " + employee.getId());
				else
					messages.add("Not saved");
			} catch (Exception e) {
				messages.add("Not saved");
			}
			messages.addAll(employeeService.calculate(employeeDto));
			return ResponseEntity.ok(messages);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
}