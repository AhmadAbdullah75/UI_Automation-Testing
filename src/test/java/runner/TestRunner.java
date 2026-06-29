package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", // Path to feature files
                tags = "@Product or @Checkout or @Form or @UI", glue = { "stepDef" }, // Package containing step
                                                                                      // definitions
                plugin = {
                                "pretty",
                                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure reporting
                                "junit:target/JUnitReports/report.xml", // JUnit report
                                "json:target/JsonReports/report.json", // JSON report
                                "html:target/HtmlReports/report.html" // HTML report
                }, monochrome = true // Makes console output readable
)
public class TestRunner {

}
