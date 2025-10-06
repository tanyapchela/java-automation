package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BasePage;

public class HomePage extends BasePage{
    private AndroidDriver driver;
    private WebDriverWait wait; 

    private final By countryDropdown = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private final By nameField = By.id("com.androidsample.generalstore:id/nameField");
    private final By maleRadio = By.id("com.androidsample.generalstore:id/radioMale");
    private final By femaleRadio = By.id("com.androidsample.generalstore:id/radioFemale");
    private final By letsShopBtn = By.id("com.androidsample.generalstore:id/btnLetsShop");
    private final By navigationBarBackground = By.id("android:id/navigationBarBackground");

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectCountry(String country) 
    {   utils.click(countryDropdown);   
          driver.findElement(AppiumBy.androidUIAutomator(         
          "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))")).click(); }

    //public void enterName(String name) {
    //    driver.findElement(nameField).sendKeys(name);
    //}

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male"))
            driver.findElement(maleRadio).click();
        else
            driver.findElement(femaleRadio).click();
    }

    public void clickLetsShop() {
        driver.findElement(letsShopBtn).click();
    }

    public void isDisplayed() {
        driver.findElement(navigationBarBackground).isDisplayed();
    }

    public void enterName(String name) {
        utils.type(nameField, name);
    }
}

