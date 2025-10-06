import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By; // для локаторів
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI; //запуск сервера
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // - анотація по ордеру тестів
public class AppTest {

    private AndroidDriver driver;

    @BeforeAll
    void setup() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp(System.getProperty("user.dir") + "/General-Store.apk") // прибрати апп
                .setAppPackage("com.androidsample.generalstore") // запуск апіум інспектор
                .setAppActivity("com.androidsample.generalstore.SplashActivity");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
    }

    @Test
    @Order(1)
    void testAppLaunch() {
        Assertions.assertTrue( // assert true - waiting
                driver.findElement(By.id("com.androidsample.generalstore:id/splashscreen")).isDisplayed());

    }

    @Test
    @Order(2)
    void frameLayout() throws InterruptedException {
        Assertions.assertTrue(
                waitForElement(5000, By.id("com.androidsample.generalstore:id/toolbar_title")).isDisplayed());
    }

    @AfterAll
    void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public WebElement waitForElement(int timeout, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout)); // webdrweb - конструктор; wait - object;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
