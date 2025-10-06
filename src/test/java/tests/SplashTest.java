package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import base.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.SplashPage;

@Epic("General Store App")
@Feature("Splash Screen")
public class SplashTest extends BasePage {
 
    @Test
    @Story("Перевіmvn allure:report\n" + //
                "рка відображення сплеша")
    @Description("Тест перевіряє, що Splash screen видно після запуску додатку")
    @Severity(SeverityLevel.CRITICAL)
    void testSplashIsDisplayed() {
        SplashPage splash = new SplashPage(driver);
        assertTrue(splash.isDisplayed(), "Splash screen should be visible");
    }
}
