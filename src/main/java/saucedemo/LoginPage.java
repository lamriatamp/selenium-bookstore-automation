package saucedemo;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {


    @FindBy(id = "user-name")
    private WebElement inputUser;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement pageTitle;


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

    public boolean isUrlContains(String expectedUrlPart) {
        try {
            // Tunggu maksimal 5 detik sampai URL benar-benar berubah
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserLoggedInSuccessfully() {
        try {
            waitForElementToBeVisible(pageTitle); // Tunggu elemen Products muncul
            return pageTitle.isDisplayed();
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

