package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends AbsBasePage{
    private String testingPageLocator ="//div[contains(text(),'Тестирование')]";

    private String eventsPageSelector = ".iWgyfi";
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public WebElement findButton(By text){
        return  driver.findElement(text);
    }
    public void clickButton(By text){
        driver.findElement(text).click();
    }
    public void clickTestingPage(){
        driver.findElement(By.xpath(testingPageLocator)).click();
    }
    public void clickTEventsPage(){
        driver.findElement(By.cssSelector(eventsPageSelector)).click();
    }

}
