package ui.pagemodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.edamwebelements.WebPageElements;
import ui.pagemodel.edamwebelements.constants.FoundationWebElements;
import webconnector.Browser;

/**
 * This is the Home Page which is designed as per Page object Model.
 */
public class Homepage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(Homepage.class);
    private final WebPageElements webPageElements;


    /**
     * Homepage Constructor method.
     * @param driver WebDriver object
     */
    public Homepage(WebDriver driver) {
        super(driver);
        LOGGER.info("Home page class constructor {}", driver.getCurrentUrl());
        PageFactory.initElements(Browser.getDriver(), this);
        webPageElements = new WebPageElements(driver);
    }

    /**
     * This is selectBrand method.
     * @param brandName brand name likes west-elm,williams-sonama, etc.
     */
    public void selectBrand(String brandName) {
        try {
            if (brandName.equalsIgnoreCase("WestElm")) {
                getAction().click(webPageElements.getWebElement(FoundationWebElements.ANCHOR_WE)).perform();
                asyncWait();
            }
            if (brandName.equalsIgnoreCase("Rejuvenation")) {
                getAction().click(webPageElements.getWebElement(FoundationWebElements.ANCHOR_RJ)).perform();
                asyncWait();
            }
        } catch (Exception e) {
            LOGGER.error("Error while clicking brand name" + brandName + "", e.getMessage());
        }
    }

    /**
     * Getting Breadcrum Text.
     * @return Return Text
     */
    public String getBreadcrumLabelText() {
        WebElement webElement = webPageElements.getWebElement(FoundationWebElements.BREADCRUMB_LABEL_TEXT);
        return  webElement.getText();
    }


}
