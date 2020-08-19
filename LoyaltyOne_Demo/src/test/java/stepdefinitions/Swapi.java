package stepdefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class Swapi extends ReusableMethods {

	private ValidatableResponse VResponse;
	private RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	private RequestSpecification requestSpec;
	private final int validStatusCode = 200;

	@Given("the endpoint is up")
	public void endpoint() {
		VResponse = RestAssured.given().when().get().then();
		VResponse.statusCode(validStatusCode);
	}

	@When("I send GET request for {string} with query parameter {string}")
	public void i_send_get_request_for_with_page_number(String string, String string2) {
		requestBuilder.setBasePath(string).addQueryParam("page", string2);
		requestBuilder.setContentType(ContentType.JSON);
		requestSpec = requestBuilder.build();
		VResponse = RestAssured.given().spec(requestSpec).when().get().then();
	}

	@Then("the status code is {int}")
	public void the_status_code_is(Integer int1) {
		
		VResponse.assertThat().statusCode(validStatusCode);
	}

	@Then("total number of characters equal to {int}")
	public void total_number_of_characters_equal_to(Integer count) {
		System.out.println("Total count of star wars characters: " + VResponse.extract().path("count"));
		Assert.assertTrue(VResponse.extract().path("count").equals(count));
	}

	@Then("it retrieves the list of star wars {string}")
	public void it_retrieves_the_list_of_star_wars(String characterNames) {
		System.out.println(VResponse.extract().path("results.name"));
		String result = VResponse.extract().path("results.name").toString();
		
		List<String> charactersList = Arrays.asList(characterNames.split(","));
		for (String characters : charactersList) {
			Assert.assertTrue(result.contains(characters));
		}

	}

	@Then("total number of planets equal to {int}")
	public void total_number_of_planets_equal_to(Integer count) {
		System.out.println("Total count of star wars planets: " + VResponse.extract().path("count"));
		Assert.assertTrue(VResponse.extract().path("count").equals(count));
	}

	@Then("it retrieves the list of star wars planet {string}")
	public void it_retrieves_the_list_of_star_wars_planet(String planetNames) {
		System.out.println(VResponse.extract().path("results.name"));
		String result = VResponse.extract().path("results.name").toString();
		String[] planetsList = planetNames.split(",");
		
		for (String planets : planetsList) {
			Assert.assertTrue(result.contains(planets));
		}
	}

	@When("I send GET request for {string}")
	public void i_send_get_request_for_with_form_parameter(String string) {
		requestBuilder.setBasePath(string);
		requestBuilder.setContentType(ContentType.JSON);
		requestSpec = requestBuilder.build();
		VResponse = RestAssured.given().spec(requestSpec).when().get().then();
	}

	@Then("it retrieves all the details of the specified character")
	public void it_retrieves_all_the_details_of_the_specified_character() throws Exception {
		String actualJsonResponse = VResponse.extract().body().asString().replaceAll("\\s", "");
		System.out.println("actual:"+actualJsonResponse);
		String expectedJsonResponse = readJsonFile("CharacterDetails").replaceAll("\\s", "");
		System.out.println("expected:"+expectedJsonResponse);
		
		Assert.assertEquals(expectedJsonResponse, actualJsonResponse);
	}

	@Then("it retrieves all the details of the specified planet")
	public void it_retrieves_all_the_details_of_the_specified_planet() throws Exception {
		String actualJsonResponse = VResponse.extract().body().asString().replaceAll("\\s", "");
		System.out.println("actual:"+actualJsonResponse);
		String expectedJsonResponse = readJsonFile("PlanetDetails").replaceAll("\\s", "");
		System.out.println("expected:"+expectedJsonResponse);
		
		Assert.assertEquals(expectedJsonResponse, actualJsonResponse);
	}

	@When("I send GET request for {string} with query parameter search= {string}")
	public void i_send_get_request_for_with_query_parameter_search_darth_vader(String referenceURI, String value) {
		requestBuilder.setBasePath(referenceURI).addQueryParam("search", value);
		requestBuilder.setContentType(ContentType.JSON);
		requestSpec = requestBuilder.build();
		VResponse = RestAssured.given().spec(requestSpec).when().get().then();
	}

	@Then("it retrieves correct details of that character {string}")
	public void it_retrieves_correct_details_of_that_character_searched(String characterName) {
		ArrayList<String>  result = VResponse.extract().path("results.name");
		Assert.assertEquals(result.size(), 1);
		Assert.assertEquals(characterName, result.get(0));
	}

	
	@Then("it retrieves correct details of that planet {string}")
	public void it_retrieves_correct_details_of_that_planet_searched(String planetName) {
		ArrayList<String> result = VResponse.extract().path("results.name");
		Assert.assertEquals(result.size(), 1);
		Assert.assertEquals(planetName, result.get(0));
	}

}
