package tests;

import base.BasePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SplashPage;

public class HomeTest extends BasePage {
    private HomePage home;
    private SplashPage splash;

    @BeforeEach
    void openSplashPage() {
        splash = new SplashPage(driver);
        assertTrue(splash.isDisplayed(), "Splash screen should be visible");
        home = new HomePage(driver);
    }

    @Test
    @Order(3)
    void testHomeScreenFlow() {
        home.selectCountry("Argentina");
        home.enterName("Viktor");
        home.selectGender("Male");
        home.clickLetsShop();
    }
}