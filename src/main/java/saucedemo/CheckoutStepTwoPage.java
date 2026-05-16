package saucedemo;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepTwoPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        waitForElementToBeVisible(finishButton);

        // 1. Scroll ke tombol continue agar masuk ke dalam layar
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", finishButton);

        // 2. Paksa klik menggunakan JavaScript (JS Click)
        js.executeScript("arguments[0].click();", finishButton);
    }
}