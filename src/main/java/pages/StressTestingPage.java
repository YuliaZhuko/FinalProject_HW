package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class StressTestingPage extends AbsBasePage{
    public StressTestingPage(WebDriver driver) {
        super(driver);
    }
    private String cardIndividualSelector = ".sc-s2pydo-1";
    private String cardDurationSelector = ".galmep>div>div:nth-of-type(2)>p";
    private String cardDescriptionSelector = ".hRFuCQ";

    private String cardFormatSelector = ".galmep>div>div:nth-of-type(3)>p";


    public String gatherCardNameFromCards() {
       return driver.findElement(By.cssSelector(cardIndividualSelector)).getText();

    }
    public String gatherCardDurationFromCards() {
        return driver.findElement(By.cssSelector(cardDurationSelector)).getText();
    }
    public String gatherCardDescriptionFromCards() {
        return driver.findElement(By.cssSelector(cardDescriptionSelector)).getText();
    }
    public String gatherCardFormatFromCard(){
        return driver.findElement(By.cssSelector(cardFormatSelector)).getText();
    }

}
