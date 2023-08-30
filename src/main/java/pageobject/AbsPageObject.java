package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbsPageObject {
    protected WebDriver driver;

    protected Actions actions;

    protected WebDriverWait webDriverWait;
    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver,Integer.parseInt(System.getProperty("wait.timeout","100")));

        PageFactory.initElements(driver, this);
    }



}
