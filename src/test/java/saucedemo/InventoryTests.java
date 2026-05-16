package saucedemo;

import core.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InventoryTests extends BaseTest {

    // Mendaftarkan Logger
    private static final Logger logger = LogManager.getLogger(InventoryTests.class);

    @Test(priority = 1, description = "Test validasi judul halaman Inventory")
    public void verifyProductInventoryTitle() {
        logger.info("Memulai test verifyProductInventoryTitle");

        // Inisialisasi halaman login menggunakan DriverManager
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login menggunakan credential dari config");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("passworduser"));

        // Inisialisasi halaman inventory
        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

        logger.info("Verify judul halaman adalah 'Products'");
        Assert.assertEquals(inventoryPage.getTitle(), "Products", "Title should be 'Products'");

        logger.info("test verifyProductInventoryTitle sudah dijalankan dengan sukses");
    }

    @Test(priority = 2, description = "Test tambah dan hapus produk dari keranjang")
    public void addAndRemoveProductFromCart() {
        logger.info("Memulai test addAndRemoveProductFromCart");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login menggunakan credential dari config");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("passworduser"));

        InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

        // Proses Add to cart
        logger.info("Menambahkan item Backpack ke dalam keranjang");
        inventoryPage.addBackpackToCart();

        logger.info("Verify badge angka keranjang berubah menjadi '1'");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Badge count should be 1");

        // Proses Remove from cart
        logger.info("Menghapus item Backpack dari keranjang");
        inventoryPage.removeBackpackFromCart();

        logger.info("Verify badge angka keranjang hilang setelah item dihapus");
        Assert.assertFalse(inventoryPage.isCartBadgePresent(), "Badge should not be present after removing item");

        logger.info("test addAndRemoveProductFromCart sudah dijalankan dengan sukses");
    }
}
