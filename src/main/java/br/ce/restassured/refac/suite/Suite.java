package br.ce.restassured.refac.suite;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.restassured.core.BaseTest;
import br.ce.restassured.tests.refac.AuthTest;
import br.ce.restassured.tests.refac.ContasTest;
import br.ce.restassured.tests.refac.MovimentacaoTest;
import br.ce.restassured.tests.refac.SaldoTest;
import io.restassured.RestAssured;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	ContasTest.class,
	MovimentacaoTest.class,
	SaldoTest.class,
	AuthTest.class
	
})
public class Suite extends BaseTest{

	@BeforeClass
	public static void login() {
		Map<String, String> login = new HashMap<>();
		login.put("email", "larissagabrieli13@hotmail.com");
		login.put("senha", "123456");
		
		String token = given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		
		RestAssured.requestSpecification.header("Authorization","JWT "+ token );
		RestAssured.get("/reset").then().statusCode(200);
	}
}
