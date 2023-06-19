package runner;

import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ui.pagemodel.login.LoginFlow;
import utils.CommonMethods;
import webconnector.Browser;

/**
 * This class will call by default.
 */
@CucumberOptions(glue = {"ui.pagemodel.steps"}, plugin = {
        "html:target/cucumber-reports/cucumber-pretty",
        "json:target/json-cucumber-reports/default/cukejson.json",
        "testng:target/testng-cucumber-reports/cuketestng.xml"},
        features = {"src/test/resources/features/regression/check"})
public class TestRunner extends AbstractTestNGCucumberParallelTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);
    private static long duration;

    /**
     * This is to load before the class load.
     */
    @BeforeClass
    public static void setup() {
        Browser browser = new Browser();
        LOGGER.info("TestRunner: Before class setup");
        try {
            browser.getEnvironmentDetails();
            browser.setUpDriver();
            WebDriver driver = Browser.getDriver();
            LoginFlow log = new LoginFlow(driver);
            LOGGER.info("Starting Test Automation on "
                    + "Instance : {}", CommonMethods.getCustomUrl());
            driver.get(CommonMethods.getCustomUrl());
            log.enterCredentialAndSelectBrand();
            duration = System.currentTimeMillis();
            LOGGER.debug("Thread Id  | Scenario Num       | Step Count");
        } catch (Exception ex) {
            LOGGER.error("Error while runner {}", ex.getMessage());
        }
    }


    /**
     * This will execute after the class load.
     * TearDown
     */
    @AfterClass
    public static void tearDown() {
        LOGGER.info("TesteRunner tear down");
        try {
            Browser.getDriver().close();
            duration = System.currentTimeMillis() - duration;
            LOGGER.debug("Completed in {} miliseconds", duration);
        } catch (Exception ex) {
            LOGGER.error("Error while tear down: {}", ex.getMessage());
        }

    }

}
