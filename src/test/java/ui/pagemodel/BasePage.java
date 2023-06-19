package ui.pagemodel;

import org.openqa.selenium.support.ui.FluentWait;
import ui.pagemodel.constants.Constants;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonMethods;

import java.awt.Robot;
import java.io.File;
import java.time.Duration;

/**
 * This is Base Class for all individual Pages class This class holding
 * driver,common webElements and explicit/Fluent wait and JavaScriptExecutor
 * objects.
 * @author jsiddiqui
 */
public class BasePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected CommonMethods common;
    /**
     * WebDriver variable.
     */
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Actions action;

    /**
     * Base class constructor method.
     * @param driver WebDriver Object to load.
     */
    public BasePage(WebDriver driver) {
        try {
            this.driver = driver;
            setWebDriverWait(new WebDriverWait(this.driver, CommonMethods.getExplicitTime()));
            FluentWait fluentWait = new FluentWait(this.driver);
            setAction(new Actions(driver));
            new Robot();
        } catch (Exception e) {
            LOGGER.error("Base page class constructor error: {}", e.getMessage());
        }
    }

    /**
     * Taking config property and wait.
     */
    protected void asyncWait() {
        try {
            Awaitility.waitAtMost(Duration.ofMillis(CommonMethods.getAsyncWaitTime()));
        } catch (Exception e) {
            LOGGER.error(Constants.ERROR_WHILE_WAITING_FOR_PAGE_ELEMENT, e.getMessage());
        }
    }

    /**
     * Verify file is uploaded.
     * @param dirPath to read file
     * @param fileName e.g. image.png
     * @return full destination path
     */
    protected String getImportFile(String dirPath, String fileName) {
        String fName = null;
        try {
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }

            for (File file : files) {
                if (file.getName().contains(fileName)) {
                    fName = dirPath + "\\" + file.getName();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error while reading downloaded file : {}", e.getMessage());
        }
        return fName;
    }

    /**
     * Perform double-click on Web Elements.
     * @param element taking from caller class
     */
    protected void doubleClick(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
                    + "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                    + "arguments[0].dispatchEvent(evt);", element);
        } catch (Exception e) {
            LOGGER.error("Error while double clicking the element : {} Message: {}", element.getText(), e.getMessage());
        }
    }

    /**
     * Find Brand Anchor.
     * @param brand abbreviation
     */
    public void selectBrand(String brand) {
        try {
            By brandXpath;
            brandXpath = By.xpath("//*[@id='west-elm-anchor']");
            switch (brand.toLowerCase()) {
                case "we":
                    brandXpath = By.xpath("//*[@id='west-elm-anchor']");
                    break;
                case "pb":
                    brandXpath = By.xpath("//*[@id='pottery-barn-anchor']");
                    break;
                case "mg":
                    brandXpath = By.xpath("//*[@id='mark-and-graham-anchor']");
                    break;
                case "rj":
                    brandXpath = By.xpath("//*[@id='rejuvenation-anchor']");
                    break;
                case "ws":
                    brandXpath = By.xpath("//*[@id='williams-sonoma-anchor']");
                    break;
                default:
                    break;
            }
            driver.findElement(brandXpath).click();
        } catch (Exception e) {
            LOGGER.error(Constants.ERROR_WHILE_SELECTING_BRAND_IN_E_DAM, e.getMessage());
        }
    }

    /**
     * Change Content View.
     * @param type e.g. filter
     */
    public void changeContentView(String type) {

        try {
            WebElement leftNavigationBtn = driver.findElement(By.id("coral-id-7"));
            leftNavigationBtn.click();
            By brandXpath;
            brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Only')]");
            switch (type.toLowerCase()) {
                case "contentonly":
                    brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Only')]");
                    break;
                case "treeview":
                    brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Content Tree')]");
                    break;
                case "navigation":
                    brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Navigation')]");
                    break;
                case "timeline":
                    brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Timeline')]");
                    break;
                case "filter":
                    brandXpath = By.xpath("//coral-selectlist-item[contains(text(),'Filter')]");
                    break;
                default:
                    break;
            }
            driver.findElement(brandXpath).click();
        } catch (Exception e) {
            LOGGER.error("Error while selecting content view: {}", e.getMessage());
        }
    }

    /**
     * Click on web element.
     * @param element to click
     * @return Boolean true in case of clicked
     */
    protected boolean elementClick(WebElement element) {
        boolean isClicked = false;
        try {
            element.click();
            isClicked = true;
        } catch (Exception e) {
            LOGGER.error("Error while element click: {}", e.getMessage());
        }
        return isClicked;
    }

    /**
     * Getter web driver wait.
     * @return jsWait
     */
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    /**
     * Setter Web Driver Wait.
     * @param webDriverWait e.g. new WebDriverWait(this.driver, CommonMethods.getExplicitTime()
     */
    public void setWebDriverWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }

    /**
     * Action e.g. click.
     * @return action
     */
    public Actions getAction() {
        return action;
    }

    /**
     * Setting in base page class constructor e.g. new Actions(driver).
     * @param action setting in contructor method.
     */
    public void setAction(Actions action) {
        this.action = action;
    }
}
