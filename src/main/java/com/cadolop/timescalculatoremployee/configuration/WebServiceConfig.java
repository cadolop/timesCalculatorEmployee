package com.cadolop.timescalculatoremployee.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Bean(name = "employee")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("EmployeePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
		wsdl11Definition.setSchema(employeeSchema);
		return wsdl11Definition;
	}

	/**
	 * This is the main method
	 * 
	 * @param application Unused.
	 * @return Nothing.
	 */
	@Bean
	public XsdSchema employeeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
	}
}