package ui.pagemodel.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.pagemodel.BasePage;
import webconnector.Browser;

/**
 * This is Login Page class which is designed as per Page object Model Design
 * pattern.
 */
public class LoginFlow extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFlow.class);

    /**
     * The Constant Brand.
     */
    private static String brand;

    /**
     * The web driver object variable.
     */
    private WebDriver wedDriver;

    @FindBy(id = "username")
    private WebElement loginText;

    @FindBy(id = "password")
    private WebElement passwordText;

    @FindBy(id = "submit-button")
    private WebElement signInBtn;

    @FindBy(xpath = "//coral-shell-homeanchor-label[contains(text(),'WSI Enterprise DAM')]")
    private WebElement homePageText;

    /**
     * This is Constructor method.
     * @param driver WebDriver Object to load.
     */
    public LoginFlow(WebDriver driver) {
        super(driver);
        PageFactory.initElements(Browser.getDriver(), this);
    }

    /**
     * This is getLoginText method.
     * @return WebElement loginText
     */
    public WebElement getLoginText() {
        return loginText;
    }

    /**
     * This is getPasswordText method.
     * @return WebElement passwordText
     */
    public WebElement getPasswordText() {
        return passwordText;
    }

    /**
     * This is getSignInBtn method.
     * @return WebElement signInBtn
     */
    public WebElement getSignInBtn() {
        return signInBtn;
    }

    /**
     * This is getHomeText method.
     * @return WebElement homePageText
     */
    public WebElement getHomeText() {
        return homePageText;
    }

    /**
     * User get.
     * @return boolean false
     */
    public boolean enterUserNamePassword() {
        try {
            String user  = System.getProperty("edam.test.user");
            String pass  = System.getProperty("edam.test.pass");
            getLoginText().sendKeys(user);
            getPasswordText().sendKeys(pass);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while entering username and password: {}", e.getMessage());
            return false;
        }
    }

    /**
     * User enter username and password to login.
     */
    public void enterCredentialAndSelectBrand() {
        try {

            enterUserNamePassword();
            signInClick();
        } catch (Exception e) {
            LOGGER.error("Error While Login application: {}", e.getMessage());
        }
    }

    /**
     * This is SignInClick method.
     * @return boolean true/false
     */
    public boolean signInClick() {
        return super.elementClick(getSignInBtn());
    }

    /**
     * This is verifyHomePage method.
     * @return isHomePage true/false
     */
    public boolean verifyHomePage() {
        boolean isHomePage = false;
        try {
            WebElement homeTxt = wedDriver
                    .findElement(By.xpath("//coral-shell-homeanchor-label[contains(text(),'WSI Enterprise DAM')]"));
            if (homeTxt.getText() != null) {
                isHomePage = true;
            }
            return isHomePage;

        } catch (Exception e) {
            LOGGER.error("Error while verify Home page: {}", e.getMessage());
            return false;
        }
    }

}
