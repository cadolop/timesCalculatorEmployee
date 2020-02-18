package com.cadolop.timescalculatoremployee.rest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CalculatorControllerRestUnitTest {

	@LocalServerPort
	private int port;

	private String uri;

	@PostConstruct
	public void init() {
		uri = "http://localhost:" + port + "/calculator-svc";
	}

	@Test
	public void calculateWhenMajorityAge() throws Exception {
		Map<String, String> request = new HashMap<>();
		request.put("firstName", "Andres A");
		request.put("lastName", "Perez P");
		request.put("identificationType", "CC");
		request.put("identification", "123456789");
		request.put("birthDate", "11/10/1978");
		request.put("startDate", "01/01/2020");
		request.put("jobTitle", "Lead");
		request.put("salary", "8000000");
		given().contentType("application/json").body(request).when().post(uri + "/calculate").then().assertThat()
				.statusCode(HttpStatus.OK.value()).extract();
	}

	public void calculateWhenNoMajorityAge() throws Exception {
		Map<String, String> request = new HashMap<>();
		request.put("firstName", "Andres A");
		request.put("lastName", "Perez P");
		request.put("identificationType", "CC");
		request.put("identification", "123456789");
		request.put("birthDate", "11/10/2019");
		request.put("startDate", "01/01/2020");
		request.put("jobTitle", "Lead");
		request.put("salary", "8000000");
		String messages = given().contentType("application/json").body(request).when().post(uri + "/calculate").then().assertThat()
				.statusCode(HttpStatus.OK.value()).extract().asString();
		assertThat(messages).isEqualTo("The Employee must be majority age");
	}

	public void calculateWhenStartDateNotValid() throws Exception {
		Map<String, String> request = new HashMap<>();
		request.put("firstName", "Andres A");
		request.put("lastName", "Perez P");
		request.put("identificationType", "CC");
		request.put("identification", "123456789");
		request.put("birthDate", "11/10/2019");
		request.put("startDate", "01/01/2020");
		request.put("jobTitle", "Lead");
		request.put("salary", "8000000");
		String messages = given().contentType("application/json").body(request).when().post(uri + "/calculate").then().assertThat()
				.statusCode(HttpStatus.OK.value()).extract().asString();
		assertThat(messages).isEqualTo("Start Date not valid");
	}
}