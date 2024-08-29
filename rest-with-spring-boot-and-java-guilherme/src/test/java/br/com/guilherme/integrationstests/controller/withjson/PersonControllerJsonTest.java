package br.com.guilherme.integrationstests.controller.withjson;

import br.com.guilherme.configs.TestsConfigs;
import br.com.guilherme.integrationstests.testcontairnes.AbstractIntegrationTest;
import br.com.guilherme.integrationstests.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static PersonVO person;

	@BeforeAll
	public static void setup(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonVO();
	}

	@Test
	@Order(1)
	public void testCreate() throws IOException {
		mockPerson();

		specification = new RequestSpecBuilder().addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_GUILHERME).setBasePath("/api/person/v1").setPort(TestsConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		var content = given().spec(specification).contentType(TestsConfigs.CONTENT_TYPE_JSON).body(person).when().post().then().statusCode(200).extract().body().asString();

		PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
		person = persistedPerson;

		assertNotNull(persistedPerson.getId());
		assertNotNull(persistedPerson.getName());
		assertNotNull(persistedPerson.getCpf());
		assertNotNull(persistedPerson.getAdress());
		assertTrue(persistedPerson.getId() > 0);

		assertEquals("Richerd", persistedPerson.getName());
		assertEquals("Rua fernando", persistedPerson.getAdress());
		assertEquals("111111", persistedPerson.getCpf());
	}

	private void mockPerson() {
		person.setName("Richerd");
		person.setAdress("Rua fernando");
		person.setCpf("111111");
	}
	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws IOException {
		mockPerson();

		specification = new RequestSpecBuilder().addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_NOTGUILHERME).setBasePath("/api/person/v1").setPort(TestsConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		var content = given().spec(specification).contentType(TestsConfigs.CONTENT_TYPE_JSON).body(person).when().post().then().statusCode(403).extract().body().asString();


		assertNotNull(content);

		assertEquals("Invalid CORS request", content);
	}
	@Test
	@Order(3)
	public void testFindById() throws IOException {
		mockPerson();

		specification = new RequestSpecBuilder().addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_GUILHERME).setBasePath("/api/person/v1").setPort(TestsConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		var content = given().spec(specification).contentType(TestsConfigs.CONTENT_TYPE_JSON).pathParam("id", person.getId()).when().get("{id}").then().statusCode(200).extract().body().asString();

		PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
		person = persistedPerson;

		assertNotNull(persistedPerson.getId());
		assertNotNull(persistedPerson.getName());
		assertNotNull(persistedPerson.getCpf());
		assertNotNull(persistedPerson.getAdress());
		assertTrue(persistedPerson.getId() > 0);

		assertEquals("Richerd", persistedPerson.getName());
		assertEquals("Rua fernando", persistedPerson.getAdress());
		assertEquals("111111", persistedPerson.getCpf());
	}

	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws IOException {
		mockPerson();

		specification = new RequestSpecBuilder().addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_NOTGUILHERME).setBasePath("/api/person/v1").setPort(TestsConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL)).addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		var content = given().spec(specification).contentType(TestsConfigs.CONTENT_TYPE_JSON).pathParam("id", person.getId()).when().get("{id}").then().statusCode(200).extract().body().asString();

		PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
		person = persistedPerson;

		assertNotNull(persistedPerson.getId());
		assertNotNull(persistedPerson.getName());
		assertNotNull(persistedPerson.getCpf());
		assertNotNull(persistedPerson.getAdress());
		assertTrue(persistedPerson.getId() > 0);

		assertEquals("Richerd", persistedPerson.getName());
		assertEquals("Rua fernando", persistedPerson.getAdress());
		assertEquals("111111", persistedPerson.getCpf());
	}

}
