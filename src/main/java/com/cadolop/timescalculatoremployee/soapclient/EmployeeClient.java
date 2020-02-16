package com.cadolop.timescalculatoremployee.soapclient;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.cadolop.timescalculatoremployee.dto.EmployeeDto;
import com.cadolop.timescalculatoremployee.model.Employee;
import com.cadolop.timescalculatoremployee.service.EmployeeService;

import io.spring.guides.gs_producing_web_service.EmployeeType;
import io.spring.guides.gs_producing_web_service.SaveEmployeeRequest;
import io.spring.guides.gs_producing_web_service.SaveEmployeeResponse;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class EmployeeClient extends WebServiceGatewaySupport {
	@Autowired
	EmployeeService employeeService;

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public Employee callSoapEmployee(EmployeeDto employeeDto) {
		SaveEmployeeRequest request = new SaveEmployeeRequest();
		BeanUtils.copyProperties(employeeDto, request);
	    SaveEmployeeResponse response = (SaveEmployeeResponse) getWebServiceTemplate()
	        .marshalSendAndReceive("http://localhost:18080/calculator-svc/ws", request,
	            new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/SaveEmployeeRequest"));
	    EmployeeType employeeType = response.getEmployeeType();
		return employeeService.convertEmployeeEmployeeTypeToModel(employeeType);
	}
}
