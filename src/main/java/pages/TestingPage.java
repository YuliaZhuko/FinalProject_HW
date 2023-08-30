package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestingPage extends AbsBasePage{
    private String courseSelector = ".sc-18q05a6-1>a";
    private String cardNameSelector = ".sc-18q05a6-1>a>h6>div";

    private String cardDurationSelector = ".sc-18q05a6-1>a>div.sc-157icee-0>div>div";


    public TestingPage(WebDriver driver) {
        super(driver);
    }

    ElementsCollection courses;
    public List<WebElement> gatherElements(){
        return driver.findElements(By.cssSelector(courseSelector));
    }

    public void takeElement(){
        System.out.println(driver.findElement(By.cssSelector(cardNameSelector)).getText());
    }
    public List <String> gatherCardNamesFromCatalog() {

        List<WebElement> cardsNameFromCatalog = driver.findElements(By.cssSelector(cardNameSelector));
        List<String> namesFromCatalog = new ArrayList<>();
        for (int i = 0; i < cardsNameFromCatalog.size(); i++) {
           namesFromCatalog.add(cardsNameFromCatalog.get(i).getText());
                 }

        return namesFromCatalog;
    }
    public List <String> gatherCardDurationFromCatalog() {

        List<WebElement> cardsDurationFromCatalog = driver.findElements(By.cssSelector(cardDurationSelector));
        List<String> durationFromCatalog = new ArrayList<>();
        for (int i = 0; i < cardsDurationFromCatalog.size(); i++) {
            durationFromCatalog.add(cardsDurationFromCatalog.get(i).getText());
           // System.out.println(durationFromCatalog.get(i));
        }

        return durationFromCatalog;
    }

    public void clickTypeStressTesting (){
        driver.findElement(By.cssSelector(cardNameSelector)).click();
    }
    public String[] splitDurationString(){
    List <String> durationFromCatalog = gatherCardDurationFromCatalog();
    String str = durationFromCatalog.get(0);
    String [] words = str.split(" · ");
    return words;
    }

    }
