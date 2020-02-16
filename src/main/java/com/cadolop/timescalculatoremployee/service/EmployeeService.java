package com.cadolop.timescalculatoremployee.service;

import java.util.List;

import com.cadolop.timescalculatoremployee.dto.EmployeeDto;
import com.cadolop.timescalculatoremployee.model.Employee;

import io.spring.guides.gs_producing_web_service.EmployeeType;
import io.spring.guides.gs_producing_web_service.SaveEmployeeRequest;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public interface EmployeeService {

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public List<String> validator(EmployeeDto employeeDto);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public List<String> calculate(Employee employee);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public Employee convertEmployeeReqToModel(SaveEmployeeRequest employeeReq);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public EmployeeType convertEmployeeModelToEmployeeType(Employee employee);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public Employee convertEmployeeEmployeeTypeToModel(EmployeeType employeeType);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public Employee callSoapEmployee(EmployeeDto employeeDto);

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public Employee saveEmployee(Employee employee);
}