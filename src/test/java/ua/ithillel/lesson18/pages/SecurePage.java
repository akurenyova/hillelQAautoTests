package ua.ithillel.lesson18.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ua.ithillel.lesson18.waiters.WaiterHelper;

public class SecurePage extends BasePage{

    @FindBy(id = "flash")
    private WebElement loggedText;

    public SecurePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLoggedText() {
        return WaiterHelper.waitForVisibilityOf(driver,loggedText).getText().trim();
    }
}
