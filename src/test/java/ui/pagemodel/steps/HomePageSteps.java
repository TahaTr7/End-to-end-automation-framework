package ui.pagemodel.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.constants.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.pagemodel.Homepage;
import utils.CommonMethods;
import webconnector.Browser;

/**
 * This class is for homepage steps.
 */
public class HomePageSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageSteps.class);

    /**
     * The Constant Homepage.
     */
    private static Homepage homePage;
    private CommonMethods commonMethod;

    /**
     * This is Constructor method.
     *
     */
    public HomePageSteps() {
        try {
            WebDriver driver = Browser.getDriver();
            homePage = new Homepage(driver);
            commonMethod = new CommonMethods(driver);
        } catch (Exception ex) {
            LOGGER.error("Error while Home page step constructor {}", ex.getMessage());
        }
    }
    
    /**
     * This is user in homepage method.
     * 
     */
    @Given("User in HomePage")
    public void userInHomePage() {
        //No Implementation
    }

    /**
     * User can perform actions on selected folder.
     * @param action is click
     * @param folderName on selected folder
     */
    @Then("User {string} on {string} folder")
    public void userNavigateToSelectedFolder(String action, String folderName) {
        CommonMethods.waitForPagetoLoad();
        if (action.equalsIgnoreCase("click")) {
            Assert.assertEquals(folderName.toLowerCase(), Constants.WEST_ELM_FOLDER);
        }
    }

}
