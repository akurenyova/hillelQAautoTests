package ua.ithillel.lesson18.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ua.ithillel.lesson18.driver.DriverSingleton;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
    }

    @AfterEach
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}
