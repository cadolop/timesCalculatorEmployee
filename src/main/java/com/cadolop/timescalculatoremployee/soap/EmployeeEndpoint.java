package com.cadolop.timescalculatoremployee.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cadolop.timescalculatoremployee.model.Employee;
import com.cadolop.timescalculatoremployee.service.EmployeeService;

import io.spring.guides.gs_producing_web_service.SaveEmployeeRequest;
import io.spring.guides.gs_producing_web_service.SaveEmployeeResponse;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@Endpoint
public class EmployeeEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveEmployeeRequest")
	@ResponsePayload
	public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
		Employee employee = employeeService.convertEmployeeReqToModel(request);
		employee = employeeService.saveEmployee(employee);
		SaveEmployeeResponse response = new SaveEmployeeResponse();
		response.setEmployeeType(employeeService.convertEmployeeModelToEmployeeType(employee));
		return response;
	}
}