package stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

	@Before()
	public static void init() {
		RestAssured.baseURI = "https://swapi.dev/";
	}
	
	
}
