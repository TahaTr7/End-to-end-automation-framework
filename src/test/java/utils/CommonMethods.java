package utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Toolkit;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.awt.event.KeyEvent;
import java.awt.Robot;


/**
 * This class can have all common methods such as wait for Page Load and file.
 * upload etc. those are used in other page level class
 *
 * @author jsiddiqui
 */
public class CommonMethods {
    /**
     * The Logger {@link Logger}.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMethods.class);


    private static String uploadPath;
    private static Long explicitTime;
    private static Long implicitTime;
    private static Long asyncWaitTime;
    private static int fileUploadTime;
    private static String brand;
    private static String customUrl;
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private Robot robot;
    private static String coast;

    /**
     * This is Constructor method.
     * @param driver WebDriver Object to load.
     * @throws AWTException when there is AWT related to Exception.
     *
     */
    public CommonMethods(WebDriver driver) throws AWTException {
        setDriver(driver);
        setJs((JavascriptExecutor) driver);
        setRobot(new Robot());
    }

    /**
     * Wait till page loading is completed.
     * @return true in case of page load completed
     */
    public static boolean waitForPagetoLoad() {
        LOGGER.info("Wait for page to load time {}", CommonMethods.getExplicitTime());
        LOGGER.info("Getting driver: {}", CommonMethods.getDriver());
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), CommonMethods.getExplicitTime());

            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = webDriver -> {
                try {
                    return ((Long) getJs().executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    LOGGER.error("Error While page wait: {}", e.getMessage());
                    return false;
                }
            };
            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = webDriver -> getJs().executeScript("return document.readyState")
                    .toString().equals("complete");
            if (wait.until(jQueryLoad)) {
                wait.until(jsLoad);
            }

        } catch (Exception ex) {
            LOGGER.error("Error while wait for page load: {}", ex.getMessage());
            return  false;
        }
        return true;
    }

    /**
     * Get Explicit Time.
     * @return explicitTime
     */
    public static Long getExplicitTime() {
        return explicitTime;
    }

    /**
     * Setter Explicit Time.
     * @param explicitTime taking from config
     */
    public static void setExplicitTime(Long explicitTime) {
        CommonMethods.explicitTime = explicitTime;
    }

    /**
     * Get Upload Path.
     * @return path value
     */
    public static String getUploadPath() {
        return uploadPath;
    }

    /**
     * Setter Upload Path.
     * @param uploadPath taking from config
     */
    public static void setUploadPath(String uploadPath) {
        CommonMethods.uploadPath = uploadPath;
    }

    /**
     * Get Implicit Time.
     * @return implicit time
     */
    public static Long getImplicitTime() {
        return implicitTime;
    }

    /**
     * Setter Implicit Time.
     * @param implicitTime taking from config
     */
    public static void setImplicitTime(Long implicitTime) {
        CommonMethods.implicitTime = implicitTime;
    }

    /**
     * Getter Async Wait Time.
     * @return async wait time
     */
    public static Long getAsyncWaitTime() {
        return asyncWaitTime;
    }

    /**
     * Setter Async Wait Time.
     * @param asyncWaitTime taking from config
     */
    public static void setAsyncWaitTime(Long asyncWaitTime) {
        CommonMethods.asyncWaitTime = asyncWaitTime;
    }

    /**
     * Getter File Upload Time.
     * @return file upload time
     */
    public static int getFileUploadTime() {
        return fileUploadTime;
    }

    /**
     * Setter File Upload Time.
     * @param fileUploadTime taking from config
     */
    public static void setFileUploadTime(int fileUploadTime) {
        CommonMethods.fileUploadTime = fileUploadTime;
    }

    /**
     * Getter Brand Name.
     * @return brand
     */
    public static String getBrand() {
        return brand;
    }

    /**
     * Setter Brand.
     * @param brand taking from config
     */
    public static void setBrand(String brand) {
        CommonMethods.brand = brand;
    }

    /**
     * Getter App Url.
     * @return appUrl
     */
    public static String getCustomUrl() {
        return customUrl;
    }

    /**
     * Setter Application Url.
     * @param customUrl taking from config
     */
    public static void setCustomUrl(String customUrl) {
        CommonMethods.customUrl = customUrl;
    }

    /**
     * Getter WebDriver.
     * @return driver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Setter Driver.
     * @param driver setting in TestRunner java calss
     */
    public static void setDriver(WebDriver driver) {
        CommonMethods.driver = driver;
    }

    /**
     * Getter JS.
     * @return javascript
     */
    public static JavascriptExecutor getJs() {
        return js;
    }

    /**
     * Setter Java Script.
     * @param js javascript
     */
    public static void setJs(JavascriptExecutor js) {
        CommonMethods.js = js;
    }

    /**
     * @param fName File name to be uploaded
     * @return Boolean, return true if its success otherwise return false
     */
    public Boolean uploadFile(StringSelection fName) {
        try {
            int wTime = CommonMethods.getFileUploadTime();
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fName, null);
            Awaitility.waitAtMost(Duration.ofMillis(wTime));
            getRobot().keyPress(KeyEvent.VK_ENTER);
            // Release Enter
            getRobot().keyRelease(KeyEvent.VK_ENTER);
            // Press CTRL+V
            getRobot().keyPress(KeyEvent.VK_CONTROL);
            getRobot().keyPress(KeyEvent.VK_V);
            // Release CTRL+V
            getRobot().keyRelease(KeyEvent.VK_CONTROL);
            getRobot().keyRelease(KeyEvent.VK_V);
            Awaitility.waitAtMost(Duration.ofMillis(wTime));
            getRobot().keyPress(KeyEvent.VK_ENTER);
            getRobot().delay(wTime);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while uploading File +" + fName.toString() + "with : ", e.getMessage());
            return false;
        }
    }

    /**
     * Getter Robot.
     * @return robot value
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Setter Robot.
     * @param robot value
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * Getter Coast.
     * @return coast value
     */
    public static String getCoast() {
        return coast;
    }

    /**
     * Setter Coast.
     * @param coast taking from config
     */
    public static void setCoast(String coast) {
        CommonMethods.coast = coast;
    }

}
