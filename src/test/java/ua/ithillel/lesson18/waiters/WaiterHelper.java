package ua.ithillel.lesson18.waiters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaiterHelper {

    private static final Duration TIMEOUT_IN_SECONDS = Duration.ofSeconds(10);

    public static WebElement waitForVisibilityOf(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, TIMEOUT_IN_SECONDS).until(ExpectedConditions
                .visibilityOf(element));
    }
}
