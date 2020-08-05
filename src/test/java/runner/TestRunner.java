package runner;

import cucumber.api.CucumberOptions;
import helper.TestBase;

@CucumberOptions(features = "src/test/java/features", glue = {"step_definition", "helper"}, monochrome = true,
        plugin = {"pretty", "com.cucumber.listener.ExtentCucumberFormatter:target/report.html"})
public class TestRunner extends TestBase {
}
