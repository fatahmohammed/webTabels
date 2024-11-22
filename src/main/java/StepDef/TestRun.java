package StepDef;


import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\Configuration\\resources", glue = {"POM"},
        monochrome = true,
        plugin = {"pretty", "junit:traget/JUnitReports/report.xml",
                "json:traget/JSONReports/report.json",
                "html:traget/HTMLReports/report.html"},
        tags = " @SmokeFeature "
//*
)
public class TestRun {
}
