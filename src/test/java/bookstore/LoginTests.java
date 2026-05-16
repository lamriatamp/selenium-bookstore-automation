package bookstore;

import core.BaseTest;
import core.DriverManager;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(bookstore.LoginTests.class); // untuk panggil log nya
    @Test(priority = 1, groups = {"smoke"}, description = "Test successful login bookstore", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLogin() {
        logger.info("Memulai test login dengan correct credential user");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login menggunakan sukses kredensial");
        LoginPage.login(config.getProperty("emailUser"), config.getProperty("passworduser"));

        logger.info("Verify tidak ada error message yang ditampilkan setelah login sukses");
        Assert.assertTrue(loginPage.isUserLoggedInSuccessfully(),
                "User should not see any error message after successful login");
        logger.info("testLogin sudah dijalankan dengan sukses");

    }
    @Test(priority = 2, groups = {"smoke"}, description = "Test Failed login bookstore", retryAnalyzer = core.RetryAnalyzer.class)
    public void testLoginFailed() {
        logger.info("Memulai test login dengan Failed credential user");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        logger.info("User login menggunakan failed kredensial");
        loginPage.login(config.getProperty("emailUser"), config.getProperty("incpassworduser"));

        logger.info("Verify ada error message yang ditampilkan setelah login failed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "User should see error message after failed login");
        logger.info("testLogin sudah dijalankan dengan gagal");

    }
}

