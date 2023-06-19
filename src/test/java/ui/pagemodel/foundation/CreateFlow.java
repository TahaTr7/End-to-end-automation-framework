package ui.pagemodel.foundation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.BasePage;
import ui.pagemodel.edamwebelements.WebPageElements;
import ui.pagemodel.edamwebelements.constants.FoundationWebElements;
import webconnector.Browser;

import java.util.List;
import java.util.Map;

/**
 * Create Button Flow Class.
 */
public class CreateFlow extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFlow.class);
    private final WebPageElements webPageElements;

    /**
     * Create flow constructor method.
     * @param driver passing in step class
     */
    public CreateFlow(WebDriver driver) {
        super(driver);
        LOGGER.info("Create flow class constructor {}", driver.getCurrentUrl());
        PageFactory.initElements(Browser.getDriver(), this);
        webPageElements = new WebPageElements(driver);
    }

    /**
     * Click on option appears after clicking create button.
     * @param actionItem provide one of below to perform action.
     *                   Import UGC, Folder, Files, Content Fragment, Live Copy.
     * @return true in case of actionPerformed
     */
    public boolean clickCreateButtonListOption(String actionItem) {
        LOGGER.info("User clicked on : {} button", actionItem);
        WebElement webElement = null;
        if (actionItem.equalsIgnoreCase(FoundationWebElements.FOUNDATIONAL_PANEL_HEADER_CREATE_FOLDER_OPTION)) {
            webElement = webPageElements.getWebElement(FoundationWebElements.A_ID_DAM_CREATE_FOLDER);
        } else if (actionItem.equalsIgnoreCase(FoundationWebElements.FOUNDATIONAL_PANEL_HEADER_CREATE_FILES_OPTION)) {
            webElement = webPageElements.getWebElement(FoundationWebElements.A_DAM_FILEUPLOAD_SELECT);
        }
        return super.elementClick(webElement);
    }

    /**
     * Click On Create Button.
     * @return true in case of success
     */
    public boolean clickOnCreateButton() {
        WebElement webElement = webPageElements.getWebElement(FoundationWebElements.FOUNDATION_ACTIONBAR_CREATE_BUTTON_XPATH);
        return super.elementClick(webElement);
    }

    /**
     * User enter data and click on save.
     * @param mapList defined in feature file.
     * @return true in case of success.
     */
    public boolean enterDataAndSave(List<Map<String, String>> mapList) {
        boolean isCreated = false;
        for (Map<String, String> list : mapList) {
            //Creating folder
            if (list.get("OptionsList").equalsIgnoreCase(FoundationWebElements.FOUNDATIONAL_PANEL_HEADER_CREATE_FOLDER_OPTION)) {
                isCreated = createFolderInEDAM(list);
            } else if (list.get("OptionsList").equalsIgnoreCase(FoundationWebElements.FOUNDATIONAL_PANEL_HEADER_CREATE_FILES_OPTION)) {
                isCreated = createFilesInEDAM(list);
            }
        }
        return isCreated;
    }

    /**
     * Filling Data and click on Save.
     * @param formDataMap taking from create step definition file.
     * @return true in case of success
     */
    private boolean createFolderInEDAM(Map<String, String> formDataMap) {
        boolean isCreated;
        webPageElements.getWebElement(FoundationWebElements.INPUT_NAME_JCR_CONTENT_JCR_TITLE)
                        .sendKeys(formDataMap.get("Title"));
        webPageElements.getWebElement(FoundationWebElements.INPUT_NAME_NAME)
                .sendKeys(formDataMap.get("Name"));
        WebElement wElmCreateButton
                = webPageElements.getWebElement(FoundationWebElements.ID_CREATEFOLDER_BUTTON_CREATE);
        isCreated = super.elementClick(wElmCreateButton);
        return isCreated;
    }

    /**
     * Upload Asset.
     * @param formDataMap form data from feature file.
     * @return true
     * TODO: Implement in upload asset story
     */
    private boolean createFilesInEDAM(Map<String, String> formDataMap) {
        //EMPTY
        return true;
    }

    /**
     * Delete selected folder or asset.
     * @param mapList data caller method
     * @return true
     * TODO: Implemenataiton required for asset file.
     */
    public boolean deleteSelectedFolderOrAsset(List<Map<String, String>> mapList) {
        boolean isCreated = false;
        for (Map<String, String> list : mapList) {
            if (list.get("OptionsList").equalsIgnoreCase(FoundationWebElements.FOUNDATIONAL_PANEL_HEADER_CREATE_FOLDER_OPTION)) {
                isCreated = deleteFolderOrAsset(list);
            }
        }
        return isCreated;
    }

    /**
     * Filling Data and click on Save.
     * @param formDataMap taking from create step definition file.
     * @return true in case of success
     * TODO: placeholder
     */
    private boolean deleteFolderOrAsset(Map<String, String> formDataMap) {
        //EMPTY
        return true;
    }
}
