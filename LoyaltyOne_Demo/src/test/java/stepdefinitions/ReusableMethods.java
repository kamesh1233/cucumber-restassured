package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpStatus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class ReusableMethods {
	
	public static Response response;
	public static RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	public static RequestSpecification requestSpec;
	public static ValidatableResponse VResponse;
	
	public static ArrayList<String> getKeysFromSchema(String str) {
		requestBuilder.setBasePath("/api/"+str+" /schema");
		requestBuilder.setContentType(ContentType.JSON);
		requestSpec = requestBuilder.build();
		VResponse = given().spec(requestSpec).when().get().then();
		ArrayList<String> keys = new ArrayList<String>();
		keys = VResponse.extract().path("required");
		return keys;
	}
	
	public static String readJsonFile(String fileName) throws Exception {
		String jsonResponse = FileUtils.readFileToString(new File("src/test/java/resources/"+fileName+".json"), StandardCharsets.UTF_8);
		return jsonResponse;
	}

	
}
