package saucedemo;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndToEndTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(EndToEndTest.class);

    @Test(priority = 1, description = "Test alur checkout lengkap dari login hingga selesai")
    public void successfulCheckoutFlow() {
        logger.info("Memulai End to End Test: Successful Checkout Flow");

        // 1. Login
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        logger.info("User login menggunakan credential dari config");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("passworduser"));

        // 2. Add products to cart
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());
        logger.info("Verify berada di halaman Products");
        Assert.assertEquals(inventoryPage.getTitle(), "Products", "Should be on Inventory Page");

        logger.info("Menambahkan Backpack dan Bike Light ke cart");
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();

        logger.info("Navigasi menuju halaman cart");
        inventoryPage.goToCart();

        // 3. Cart - Proceed to checkout
        CartPage cartPage = new CartPage(DriverManager.getDriver());
        logger.info("Klik tombol Checkout di halaman cart");
        cartPage.clickCheckout();

        // 4. Checkout Step One - Information
        CheckoutStepOnePage checkoutStepOne = new CheckoutStepOnePage(DriverManager.getDriver());
        logger.info("Mengisi form informasi pengiriman (First Name, Last Name, Zip Code)");
        checkoutStepOne.fillInformation("John", "Doe", "12345");

        // --- TAMBAHKAN INI SEMENTARA UNTUK DEBUGGING ---
        try {
            Thread.sleep(3000); // Berhenti 3 detik agar kamu bisa melihat layarnya
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ----------------------------------------------

        // 5. Checkout Step Two - Overview
        CheckoutStepTwoPage checkoutStepTwo = new CheckoutStepTwoPage(DriverManager.getDriver());
        checkoutStepTwo.clickFinish();

        // 6. Checkout Complete - Verification
        CheckoutCompletePage checkoutComplete = new CheckoutCompletePage(DriverManager.getDriver());
        logger.info("Verify pesan sukses muncul di layar");
        Assert.assertEquals(checkoutComplete.getCompleteMessage(), "Thank you for your order!", "Checkout complete message mismatch");

        logger.info("End to End Test berhasil diselesaikan");
    }
}