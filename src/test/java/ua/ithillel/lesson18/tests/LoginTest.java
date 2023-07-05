package ua.ithillel.lesson18.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ua.ithillel.lesson18.pages.LoginPage;

public class LoginTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = {"/validCreds.csv"}, delimiter = ';')
    @DisplayName("Verify Login Positive Test")
    public void verifyLoginPositiveTest(String username, String password) {
        String expectedloggedText = "You logged into a secure area!\n×";
        String actualLoggedText = new LoginPage(driver)
                .openPage()
                .setUsername(username)
                .setPassword(password)
                .clickLoginButton()
                .getLoggedText();
        Assertions.assertEquals(actualLoggedText, expectedloggedText);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/invalidCreds.csv"}, delimiter = ';')
    @DisplayName("Verify Login Negative Test")
    public void verifyLoginNegativeTest(String username, String password) {
        String expectedloggedText = "Your username is invalid!\n×";
        String actualLoggedText = new LoginPage(driver)
                .openPage()
                .setUsername(username)
                .setPassword(password)
                .clickLoginButton()
                .getLoggedText();
        Assertions.assertEquals(actualLoggedText, expectedloggedText);
    }
}
