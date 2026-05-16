package bookstore;


import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    //Object

    @FindBy(id = "email")
    private static WebElement inputEmail;

    @FindBy(id = "password")
    private static WebElement passwordInput;

    @FindBy(id = "submit")
    private static WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorAlert;

    @FindBy(xpath = "//h1[@class='mt-3']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//b[normalize-space()='Incorrect password']\n")
    private WebElement popUpError;





    public LoginPage(WebDriver driver) {super(driver);
    }

     public static void login(String email, String password) {
        scrollToElement(inputEmail);
        waitForElementToBeVisible(inputEmail);
        inputEmail.sendKeys(email);
        passwordInput.sendKeys(password);
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].click();", loginButton);
    }

    public boolean isUserLoggedInSuccessfully() {
        scrollToElement(welcomeMessage);
        waitForElementToBeVisible(welcomeMessage);
        return welcomeMessage.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        scrollToElement(popUpError);
        waitForElementToBeVisible(popUpError);
        return popUpError.isDisplayed();
    }



}

