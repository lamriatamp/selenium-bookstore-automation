package saucedemo;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    // --- MENGGUNAKAN @FindBy SESUAI STANDAR FRAMEWORK KAMU ---

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartIcon;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    @FindBy(css = ".inventory_item_name")
    private WebElement firstItemName;

    // Constructor
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // --- METHODS ---

    public String getTitle() {
        waitForElementToBeVisible(title);
        return title.getText();
    }

    public void addBackpackToCart() {
        waitForElementToBeVisible(addBackpackToCartButton);
        addBackpackToCartButton.click();
    }

    public void addBikeLightToCart() {
        waitForElementToBeVisible(addBikeLightToCartButton);
        addBikeLightToCartButton.click();
    }

    public void removeBackpackFromCart() {
        waitForElementToBeVisible(removeBackpackButton);
        removeBackpackButton.click();
    }

    public void goToCart() {
        waitForElementToBeVisible(shoppingCartIcon);
        shoppingCartIcon.click();
    }

    public String getFirstItemName() {
        waitForElementToBeVisible(firstItemName);
        return firstItemName.getText();
    }

    public String getCartBadgeCount() {
        try {
            waitForElementToBeVisible(shoppingCartBadge);
            return shoppingCartBadge.getText();
        } catch (Exception e) {
            return "0"; // Return "0" jika badge tidak ada
        }
    }

    // Menggunakan try-catch sama seperti isErrorMessageDisplayed milikmu
    public boolean isCartBadgePresent() {
        try {
            return shoppingCartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
