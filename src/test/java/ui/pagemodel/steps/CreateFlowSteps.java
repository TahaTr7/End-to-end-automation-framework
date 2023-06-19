package ui.pagemodel.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ui.pagemodel.foundation.CreateFlow;
import utils.CommonMethods;
import webconnector.Browser;

import java.util.List;
import java.util.Map;

/**
 * Create Folder.
 */
public class CreateFlowSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFlowSteps.class);


    /**
     * The Constant Homepage.
     */
    private static CreateFlow createFolderFlow;

    /**
     * CreateFlow Constructor Method.
     */
    public CreateFlowSteps() {
        try {
            WebDriver driver = Browser.getDriver();
            createFolderFlow = new CreateFlow(driver);
            new CommonMethods(driver);
        } catch (Exception ex) {
            LOGGER.error("Error while Create flow step constructor {}",  ex.getMessage());
        }
    }

    /**
     * This is user navigate to corresponding brand page.
     */
    @When("User click on create button")
    public void userClickOnCreateButton() {
        try {
            CommonMethods.waitForPagetoLoad();
            boolean isClicked = createFolderFlow.clickOnCreateButton();
            Assert.assertTrue(isClicked);
        } catch (Exception ex) {
            LOGGER.error("Error while click on create button: {}", ex.getMessage());
        }
    }

    /**
     * Getting Options List and click.
     * @param table is options list.
     */
    @And("click any option from the list")
    public void clickAnyOptionFromTheList(DataTable table) {
        try {
            CommonMethods.waitForPagetoLoad();
            List<Map<String, String>> mapList = table.asMaps();
            for (Map<String, String> list : mapList) {
                boolean isClicked = createFolderFlow.clickCreateButtonListOption(list.get("OptionsList"));
                Assert.assertTrue(isClicked);
            }
        } catch (Exception ex) {
            LOGGER.error("Error while click on any option in create button: {}", ex.getMessage());
        }
    }

    /**
     * User enters data in the form and save.
     * @param formDataTable to fill in selected operation.
     */
    @And("user enters data in the form and save")
    public void userEntersDataInFolderCreationFormAndSave(DataTable formDataTable) {
        try {
            CommonMethods.waitForPagetoLoad();
            List<Map<String, String>> mapList = formDataTable.asMaps();
            boolean isClicked = createFolderFlow.enterDataAndSave(mapList);
            Assert.assertTrue(isClicked);
        } catch (Exception ex) {
            LOGGER.error("Error while enter data in form and save: {}", ex.getMessage());
        }
    }

    /**
     * user delete selected folder or file.
     * @param table taking from feature file
     */
    @Then("user delete selected folder or asset")
    public void userDeleteSelectedFolderOrAsset(DataTable table) {
        try {
            CommonMethods.waitForPagetoLoad();
            List<Map<String, String>> mapList = table.asMaps();
            boolean isDeleted = createFolderFlow.deleteSelectedFolderOrAsset(mapList);
            Assert.assertTrue(isDeleted);
        } catch (Exception ex) {
            LOGGER.error("Error while delete selected folder or asset: {}", ex.getMessage());
        }
    }
}