package saucedemo;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        waitForElementToBeVisible(checkoutButton);
            // 1. Scroll ke tombol continue agar masuk ke dalam layar
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        // 2. Paksa klik menggunakan JavaScript (JS Click)
        js.executeScript("arguments[0].click();", checkoutButton);
    }
}