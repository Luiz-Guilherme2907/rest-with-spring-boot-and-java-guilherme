package br.com.guilherme.integrationstests.swagger;

import static io.restassured.RestAssured.given;

import br.com.guilherme.configs.TestsConfigs;
import br.com.guilherme.integrationstests.testcontairnes.AbstractIntegrationTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.QAbstractAuditable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUiPage() {
		var content = given().basePath("/swagger-ui/index.html").port(TestsConfigs.SERVER_PORT).when().get().then().statusCode(200).extract().body().asString();

		assertTrue(content.contains("Swagger UI"));
	}

}
