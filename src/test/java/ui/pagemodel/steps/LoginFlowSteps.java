package ui.pagemodel.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import ui.pagemodel.login.LoginFlow;
import webconnector.Browser;

/**
 * This class is to Login steps.
 * 
 */
public class LoginFlowSteps {
    private static LoginFlow loginFlow;

    /**
     * The Constant UserName.
     */
    private static String userName;

    /**
     * The Constant Password.
     */
    private static String password;
    /**
     * This is Constructor method.
     *
     */
    public LoginFlowSteps() {
        loginFlow = new LoginFlow(Browser.getDriver());
    }
    
    /**
     * This is to user navigate to login page.
     * 
     */
    @Given("User Navigate to Login Page")
    public void userNavigateToLoginPage() {

    }
    
    /**
     * This is to user enter user credentials.
     * 
     */
    @And("Enter User Credentials")
    public void enterUserCredentials() {
        boolean isSuccess = loginFlow.enterUserNamePassword();
        Assert.assertTrue(isSuccess);
    }
    
    /**
     * This is to user clicks sign in button.
     * 
     */
    @And("User Clicks SignIn Button")
    public void userClicksSignInButton() {
        boolean isSuccess = loginFlow.signInClick();
        Assert.assertTrue(isSuccess);
    }
    
    /**
     * This is to user should be homepage.
     * 
     */
    @Then("User Should See home page")
    public void userShouldSeeHomepage() {
        boolean isSuccess = loginFlow.verifyHomePage();
        Assert.assertTrue(isSuccess);
    }

}
