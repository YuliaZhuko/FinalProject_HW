package main;

import data.CourseData;
import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.StressTestingPage;
import pages.TestingPage;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class TestingPageTest {

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
    public void checkCoursesTestingPage() throws InterruptedException {
        log.debug("Тестовое сообщение");
        MainPage mainPage = new MainPage(driver);
        mainPage.open("");
        mainPage.clickTestingPage();
        Thread.sleep(5000);
        TestingPage testingPage = new TestingPage(driver);
        Assertions.assertEquals(testingPage.gatherElements().size(), Integer.parseInt(CourseData.NUMBER_OF_COURSES.getTitle()),"Количество курсов по тестированию изменилось");
        List<String> namesFromCatalog = testingPage.gatherCardNamesFromCatalog();

        List <String> durationFromCatalog = testingPage.gatherCardDurationFromCatalog();
        String [] words = testingPage.splitDurationString();


        testingPage.clickTypeStressTesting();
        StressTestingPage stressTestingPage = new StressTestingPage(driver);
        stressTestingPage.gatherCardNameFromCards();
        Assertions.assertEquals( namesFromCatalog.get(0),stressTestingPage.gatherCardNameFromCards(), "Название курса нагрузочное тестирование на карточке изменилось");
        Assertions.assertEquals(words[1],stressTestingPage.gatherCardDurationFromCards(), "Продолжительность карса Нагрузочное тестирование на карточке изменилась");
        Assertions.assertEquals(CourseData.STRESS_TESTING_DESCRIPTION.getTitle(),stressTestingPage.gatherCardDescriptionFromCards(),"Описание курса Нагрузочное тестирование на карточке изменилось");
        Assertions.assertEquals(CourseData.COURSE_FORMAT.getTitle(),stressTestingPage.gatherCardFormatFromCard(),"Формат курса Нагрузочное тестирование указанный на карточке изменился");

    }


}
