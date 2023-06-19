package ui.pagemodel.edamwebelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ui.pagemodel.BasePage;
import webconnector.Browser;


/**
 *
 */
public class WebPageElements extends BasePage {
    private final WebDriver driver;
    /**
     * Finding WebElements.
     * @param driver taking from caller class
     */
    public WebPageElements(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(Browser.getDriver(), this);
    }

    /**
     * Get WebElement from xPath.
     * @param xpath taking from caller class
     * @return WebElement
     */
    public WebElement getWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
}
