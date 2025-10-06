package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonUtils {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CommonUtils(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Очікування елемента
    protected WebElement waitForElement(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Не вдалося знайти елемент: " + locator + " | Причина: " + e.getMessage());
            throw e;
        }
    }

    // Клік по елементу
    public void click(By locator) {
        try {
            waitForElement(locator ).click();
            System.out.println(" Клік по елементу: " + locator);
        } catch (Exception e) {
            System.out.println(" Помилка кліку по елементу: " + locator + " | Причина: " + e.getMessage());
            throw e;
        }
    }

    // Введення тексту
    public void type(By locator, String text) {
        try {
            WebElement el = waitForElement(locator);
            if (el != null) {
                el.clear();
                el.sendKeys(text);
                System.out.println("[INFO] Введено текст '" + text + "' у поле: " + locator);
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Не вдалося ввести текст у поле: " + locator + " -> " + e.getMessage());
        }
    }

    // Отримати текст
    public String getText(By locator) {
        try {
            WebElement el = waitForElement(locator);
            if (el != null) {
                String text = el.getText();
                System.out.println("[INFO] Отримано текст з елемента " + locator + ": " + text);
                return text;
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Не вдалося отримати текст з елемента: " + locator + " -> " + e.getMessage());
        }
        return null;
    }

    // Перевірка чи елемент відображається
    public boolean isElementDisplayed(By locator) {
        try {
            boolean displayed = driver.findElement(locator).isDisplayed();
            System.out.println("[INFO] Елемент " + locator + " відображається: " + displayed);
            return displayed;
        } catch (NoSuchElementException e) {
            System.out.println("[WARN] Елемент не знайдено: " + locator);
            return false;
        }
    }
}

