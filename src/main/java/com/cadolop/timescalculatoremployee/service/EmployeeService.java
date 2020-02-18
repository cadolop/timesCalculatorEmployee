package com.cadolop.timescalculatoremployee.service;

import java.util.List;

import com.cadolop.timescalculatoremployee.dto.EmployeeDto;
import com.cadolop.timescalculatoremployee.model.Employee;

import io.spring.guides.gs_producing_web_service.EmployeeType;
import io.spring.guides.gs_producing_web_service.SaveEmployeeRequest;

/**
 * The interface of employee service
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public interface EmployeeService {

	/**
	 * This is the validator method of DTO
	 * 
	 * @param employeeDto data transfered of front to validate.
	 * @return the list of validation messages.
	 */
	public List<String> validator(EmployeeDto employeeDto);

	/**
	 * This is the calculate method of days since start job and birth date
	 * 
	 * @param employeeDto with the data model.
	 * @return list of messages with the days since start job and birth date.
	 */
	public List<String> calculate(EmployeeDto employeeDto);

	/**
	 * This method is to convert employee request soap to model 
	 * 
	 * @param employeeReq the request soap.
	 * @return the employee model.
	 */
	public Employee convertEmployeeReqToModel(SaveEmployeeRequest employeeReq);

	/**
	 * This method is to convert employee model to employee type soap
	 * 
	 * @param employee to convert to employee type soap.
	 * @return the employee type soap.
	 */
	public EmployeeType convertEmployeeModelToEmployeeType(Employee employee);

	/**
	 * This method is to convert employee type soap to model
	 * 
	 * @param employeeType soap to convert to employee.
	 * @return the employee model.
	 */
	public Employee convertEmployeeTypeToModel(EmployeeType employeeType);

	/**
	 * This method is call soap client to save employee in database
	 * 
	 * @param employeeDto to send request soap.
	 * @return the employee saved in data base.
	 */
	public Employee callSoapEmployee(EmployeeDto employeeDto);

	/**
	 * This method to save employee repository in database  
	 * 
	 * @param employee data to save in database.
	 * @return the employee saved.
	 */
	public Employee saveEmployee(Employee employee);
}