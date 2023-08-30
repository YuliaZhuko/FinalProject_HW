package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.AbsPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbsBasePage extends AbsPageObject {

    private final static String BASE_URL = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {super(driver);}

    public void open(String path) { driver.get(BASE_URL + path);}


}
