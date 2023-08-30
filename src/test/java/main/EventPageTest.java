package main;

import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import exceptions.MonthException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EventPage;
import pages.MainPage;

import java.text.ParseException;

public class EventPageTest {
    private WebDriver driver;
    private Logger log = (Logger) LogManager.getLogger(TestingPageTest.class);

    @BeforeEach
    public void init() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
    }

    @AfterEach
    public void close (){
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }
    @Test
    public void checkEventPage() throws MonthException, ParseException {
        log.debug("Тестовое сообщение");
        MainPage mainPage = new MainPage(driver);
        mainPage.open("");
        mainPage.clickTEventsPage();
        EventPage eventPage = new EventPage(driver);
        eventPage.gatherTypeOpenVebinar();
       Assertions.assertNull(eventPage.compareType(), "Мероприятия со следующими индексами не являются открытими вебинарами");
       Assertions.assertTrue( eventPage.compareCurrentDateWithEvents());
    }

}
