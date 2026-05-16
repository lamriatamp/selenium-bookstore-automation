package saucedemo;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {


    @FindBy(id = "user-name")
    private WebElement inputUser;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement buttonSubmit;

    @FindBy(css = ".error-message-container.error")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForElementToBeVisible(inputUser);
        inputUser.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
    }

    public boolean isUserLoggedInSuccessfully() {
        try {
            waitForElementToBeVisible(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitForElementToBeVisible(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getErrorMessage() {
        try {
            waitForElementToBeVisible(errorMessage);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}

