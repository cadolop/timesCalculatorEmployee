package com.cadolop.timescalculatoremployee.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cadolop.timescalculatoremployee.dto.EmployeeDto;
import com.cadolop.timescalculatoremployee.model.Employee;
import com.cadolop.timescalculatoremployee.repository.EmployeeRepository;
import com.cadolop.timescalculatoremployee.service.EmployeeService;
import com.cadolop.timescalculatoremployee.soapclient.EmployeeClient;
import com.cadolop.timescalculatoremployee.util.DateUtil;

import io.spring.guides.gs_producing_web_service.EmployeeType;
import io.spring.guides.gs_producing_web_service.SaveEmployeeRequest;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class.getName());
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ApplicationContext appContext;
	
	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public List<String> validator(EmployeeDto employeeDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		List<String> messages = new ArrayList<>();
		String msg = "";
		for (ConstraintViolation<EmployeeDto> violation : violations) {
			LOG.log(Level.WARNING, violation.getMessage());
			messages.add(violation.getMessage());
		}
		Date birthDate = DateUtil.converStringToDate(employeeDto.getBirthDate());
		Date startDate = DateUtil.converStringToDate(employeeDto.getStartDate());
		if (!isMajorityAge(birthDate)) {
			msg = "The Employee must be majority age";
			LOG.log(Level.WARNING, msg);
			messages.add(msg);
		}
		if (!validStartDate(startDate)) {
			msg = "Start Date not valid";
			LOG.log(Level.WARNING, msg);
			messages.add(msg);
		}
		return messages;
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public List<String> calculate(Employee employee) {
		List<String> messages = new ArrayList<>();
		Period agePeriod = Period.between(DateUtil.convertDateToLocalDate(employee.getBirthDate()), LocalDate.now());
		Period startJobPeriod = Period.between(DateUtil.convertDateToLocalDate(employee.getStartDate()), LocalDate.now());
		messages.add("Age Period = " + agePeriod.getYears() + "," + agePeriod.getMonths() + "," + agePeriod.getDays());
		messages.add("Start Job Period = " + startJobPeriod.getYears() + "," + startJobPeriod.getMonths() + "," + startJobPeriod.getDays());
		return messages;		
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public EmployeeType convertEmployeeModelToEmployeeType(Employee employee) {
		GregorianCalendar calendar = new GregorianCalendar();
		try {
			EmployeeType employeeType = new EmployeeType();
			employeeType.setId(employee.getId());
			employeeType.setFirstName(employee.getFirstName());
			employeeType.setLastName(employee.getLastName());
			employeeType.setIdentificationType(employee.getIdentificationType());
			employeeType.setIdentification(employee.getIdentification());
			calendar.setTime(employee.getBirthDate());
			employeeType.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			calendar.setTime(employee.getStartDate());
			employeeType.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
			employeeType.setJobTitle(employee.getJobTitle());
			employeeType.setSalary(new Double(employee.getSalary()));
			return employeeType;
		} catch (DatatypeConfigurationException e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public Employee convertEmployeeEmployeeTypeToModel(EmployeeType employeeType) {
		Employee employee = new Employee();
		employee.setId(employeeType.getId());
		employee.setFirstName(employeeType.getFirstName());
		employee.setLastName(employeeType.getLastName());
		employee.setIdentificationType(employeeType.getIdentificationType());
		employee.setIdentification(employeeType.getIdentification());
		employee.setBirthDate(employeeType.getBirthDate().toGregorianCalendar().getTime());
		employee.setStartDate(employeeType.getStartDate().toGregorianCalendar().getTime());
		employee.setJobTitle(employeeType.getJobTitle());
		employee.setSalary(new Double(employeeType.getSalary()));
		return employee;
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public Employee callSoapEmployee(EmployeeDto employeeDto) {
		EmployeeClient employeeClient = (EmployeeClient) appContext.getBean("employeeClient");
		return employeeClient.callSoapEmployee(employeeDto);
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public Employee convertEmployeeReqToModel(SaveEmployeeRequest employeeReq) {
		Employee employee = new Employee();
		employee.setFirstName(employeeReq.getFirstName());
		employee.setLastName(employeeReq.getLastName());
		employee.setIdentificationType(employeeReq.getIdentificationType());
		employee.setIdentification(employeeReq.getIdentification());
		employee.setBirthDate(DateUtil.converStringToDate(employeeReq.getBirthDate()));
		employee.setStartDate(DateUtil.converStringToDate(employeeReq.getStartDate()));
		employee.setJobTitle(employeeReq.getJobTitle());
		employee.setSalary(new Double(employeeReq.getSalary()));
		return employee;
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	private boolean isMajorityAge(Date birthDate) {
		return (Period.between(DateUtil.convertDateToLocalDate(birthDate), LocalDate.now()).getYears() >= 18);
	}

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	private boolean validStartDate(Date startDate) {
		return (Period.between(DateUtil.convertDateToLocalDate(startDate), LocalDate.now()).getDays() >= 0);
	}
}