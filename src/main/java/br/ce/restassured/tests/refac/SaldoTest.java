package br.ce.restassured.tests.refac;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.ce.restassured.core.BaseTest;
import br.ce.restassured.utils.BarrigaUtils;

public class SaldoTest extends BaseTest{
	
	
	@Test
	public void deveCalcularSaldoContas() {	
		Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para saldo");
		given()
		.when()
			.get("/saldo")
		.then()
			.log().all()
			.statusCode(200)
			.body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"));
		
	}
	
	

}
