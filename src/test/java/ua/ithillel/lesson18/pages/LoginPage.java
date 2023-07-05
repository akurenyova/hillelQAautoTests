package ua.ithillel.lesson18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ua.ithillel.lesson18.waiters.WaiterHelper;

public class LoginPage extends BasePage{

    public final String HOMEPAGE = "https://the-internet.herokuapp.com/login";

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage openPage() {
        driver.get(HOMEPAGE);
        return this;
    }

    public LoginPage setUsername(String username) {
        WaiterHelper.waitForVisibilityOf(driver, usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        WaiterHelper.waitForVisibilityOf(driver, passwordInput).sendKeys(password);
        return this;
    }

    public SecurePage clickLoginButton() {
        WaiterHelper.waitForVisibilityOf(driver, loginButton).click();
        return new SecurePage(driver);
    }
}
