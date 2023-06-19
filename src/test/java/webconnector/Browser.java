package webconnector;

import ui.pagemodel.constants.Constants;
import io.cucumber.core.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class is used to initialize the web driver, based on the browser type
 * in the application.properties file.
 * @param <V> version
 */
public class Browser<V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Browser.class);
    private static WebDriver driver;
    private static Properties applicationProp = new Properties();

    /**
     * This is Constructor method.
     */
    public Browser() {
        FileInputStream fis;
        try {
            String filepath = System.getProperty("user.dir") + "/src/test/resources/application.properties";
            File fileName = new File(filepath);
            fis = new FileInputStream(fileName);
            applicationProp = new Properties();
            applicationProp.load(fis);
            getEnvironmentDetails();
        } catch (Exception e) {
            LOGGER.error(Constants.ERROR_WHILE_READING_APPLICATION_PROPERTIES_FILE, e.getMessage());
        }
    }

    /**
     * This is getter method.
     * @return driver WebDriver Object to load.
     *
     */
    public static WebDriver getDriver() {
        return driver;
    }


    /**
     * This is switch tab method.
     * @param tabNo tab number.
     */
    public static void switchTab(int tabNo) {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(tabNo));
    }

    /**
     * This is to get current tab index method.
     * @return index
     */
    public static int getCurrentTabIndex() {
        String currentTab = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        return tabs.indexOf(currentTab);
    }

    /**
     * This is to set up the driver.
     */
    public void setUpDriver() {
        try {
            LOGGER.info("Browser driver setup");
            String browser = applicationProp.getProperty("browser");
            LOGGER.info("Setting up driver for {}", browser);
            if (browser == null) {
                browser = "chrome";
            }
            boolean isHeadless = Boolean.parseBoolean(applicationProp.getProperty("headless"));
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    if (isHeadless) {
                        chromeOptions.addArguments("headless");
                    }
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(isHeadless);
                    driver = new FirefoxDriver(options);
                    driver.manage().window().maximize();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    SafariOptions safariOptions = new SafariOptions();
                    driver = new SafariDriver(safariOptions);
                    driver.manage().window().maximize();
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
            }
        } catch (Exception ex) {
          LOGGER.error("Error while setup drivers {}", ex.getMessage());
        }

    }

    /**
     * This method will used to take a screenshot while execution.
     * @param scenario to pass exact scenario
     */
    private void saveScreenshotsForScenario(final Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    /**
     * Read application config.
     */
    public void getEnvironmentDetails() {
        try {
            CommonMethods.setUploadPath(System.getProperty("user.dir") + applicationProp.getProperty("upload.path"));
            CommonMethods.setCustomUrl(applicationProp.getProperty("custom.url"));
            CommonMethods.setAsyncWaitTime(Long.parseLong(applicationProp.getProperty("asyncTime")));
            CommonMethods.setImplicitTime(Long.parseLong(applicationProp.getProperty("implicitTime")));
            CommonMethods.setExplicitTime(Long.parseLong(applicationProp.getProperty("explicitTime")));
            CommonMethods.setBrand(applicationProp.getProperty("brand"));
            CommonMethods.setCoast(applicationProp.getProperty("coast"));
            CommonMethods.setFileUploadTime(Integer.parseInt(applicationProp.getProperty("fileUploadTime")));
        } catch (Exception e) {
            LOGGER.error("Error while configuring common data: {}", e.getMessage());
        }
    }

}
