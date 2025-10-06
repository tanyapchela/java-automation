package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.CommonUtils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;

public class BasePage {
    protected static AndroidDriver driver;
    protected static WebDriverWait wait;
    protected static CommonUtils utils;

    @BeforeAll
    static void setup() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp(System.getProperty("user.dir") + "/General-Store.apk")
                .setAppPackage("com.androidsample.generalstore")
                .setAppActivity("com.androidsample.generalstore.SplashActivity");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        utils = new CommonUtils(driver);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            try {
                driver.removeApp("com.androidsample.generalstore");
                System.out.println("Додаток видалено");
            } catch (Exception e) {
                System.out.println("Не вдалося видалити додаток: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }
}
