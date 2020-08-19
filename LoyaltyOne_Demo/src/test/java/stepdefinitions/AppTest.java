package stepdefinitions;

import java.time.LocalDate;
import java.util.Date;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",plugin = { "pretty",
		"html:target/Test_Reports.html" }, monochrome = true)
public class AppTest {

}