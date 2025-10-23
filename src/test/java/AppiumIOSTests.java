import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppiumIOSTests {
    
    private IOSDriver driver;
    private AppiumDriverLocalService service;
    
    @BeforeAll
    public void setUp() throws Exception {
     
        // Налаштування для iOS симулятора
        io.appium.java_client.ios.options.XCUITestOptions options = 
            new io.appium.java_client.ios.options.XCUITestOptions();
        
        options.setCapability("platformName", "iOS");
        options.setCapability("platformVersion", "18.6");   
        options.setCapability("deviceName", "iPhone 16 Plus");  
        options.setCapability("automationName", "XCUITest");
        
        // Шлях до .app файлу
        options.setCapability("app", "/Users/IUAR0170/projects/test/generalstore/TheApp.app");
        
        // Підключення до Appium сервера
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("Appium сесія запущена");
    }
    
    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✓ Appium сесія закрита");
        }
        
           }
    
    @Test
    public void test_01_AppLaunch() {
        System.out.println("Тест 1: Запуск додатку ===");
        try {
            ApplicationState appState = driver.queryAppState("com.cloudinary.theapp");
            System.out.println("Додаток успішно запустився. Стан: " + appState);
            assert appState != null;
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    
    @Test
    public void test_02_FindElements() {
        System.out.println("Тест 2: Пошук елементів ===");
        try {
            List<WebElement> buttons = driver.findElements(AppiumBy.xpath("//XCUIElementTypeButton"));
            System.out.println("Знайдено " + buttons.size() + " кнопок");
            assert buttons.size() > 0;
        } catch (Exception e) {
            System.out.println("Елементи не знайдені");
        }
    }
    
    @Test
    public void test_03_TapButton() {
        System.out.println("Тест 3: Натискання на кнопку ===");
        try {
            WebElement button = driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='Login']"));
            button.click();
            System.out.println(" Кнопка натиснута");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Кнопка не знайдена: " + e.getMessage());
        }
    }
    
    @Test
    public void test_04_InputText() {
        System.out.println("Тест 4: Введення тексту ===");
        try {
            WebElement inputField = driver.findElement(AppiumBy.xpath("//XCUIElementTypeTextField"));
            inputField.clear();
            inputField.sendKeys("test@gmail.com");
            System.out.println("Текст введено успішно");
        } catch (Exception e) {
            System.out.println("Помилка введення тексту: " + e.getMessage());
        }
    }
    
    @Test
    public void test_05_VerifyText() {
        System.out.println("Тест 5: Перевірка тексту на екрані ===");
        try {
            List<WebElement> textElements = driver.findElements(
                AppiumBy.xpath("//XCUIElementTypeStaticText")
            );
            System.out.println("Знайдено " + textElements.size() + " текстових елементів");
            
            for (WebElement elem : textElements) {
                if (!elem.getText().isEmpty()) {
                    System.out.println("  - " + elem.getText());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    
    @Test
    public void test_06_SwipeAction() {
        System.out.println("Тест 6: Свайп по екрану ===");
        try {
            Dimension size = driver.manage().window().getSize();
            int startX = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);
            
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, null, startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), null, startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            
            driver.perform(java.util.Collections.singletonList(sequence));
            System.out.println("Свайп виконано успішно");
        } catch (Exception e) {
            System.out.println("Помилка при свайпі: " + e.getMessage());
        }
    }
    
    @Test
    public void test_07_WaitForElement() {
        System.out.println("Тест 7: Очікування елемента ===");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//XCUIElementTypeButton")
                )
            );
            System.out.println("Елемент з'явився в очікуваний час");
            assert element != null;
        } catch (Exception e) {
            System.out.println("Елемент не з'явився: " + e.getMessage());
        }
    }
    
    @Test
    public void test_08_Screenshot() {
        System.out.println("Тест 8: Захоплення скріншоту ===");
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get("screenshot.png"),
                driver.getScreenshotAs(org.openqa.selenium.OutputType.BYTES)
            );
            System.out.println("Скріншот збережено: screenshot.png");
        } catch (Exception e) {
            System.out.println("Помилка при збереженні скріншоту: " + e.getMessage());
        }
    }
    
    @Test
    public void test_09_GetPageSource() {
        System.out.println("Тест 9: Отримання структури сторінки ===");
        try {
            String pageSource = driver.getPageSource();
            System.out.println(" Джерело сторінки отримано (" + pageSource.length() + " символів)");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    
    @Test
    public void test_10_DeviceOrientation() {
        System.out.println("Тест 10: Перевірка орієнтації пристрою ===");
        try {
            String orientation = driver.getOrientation().value();
            System.out.println(" Поточна орієнтація: " + orientation);
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
