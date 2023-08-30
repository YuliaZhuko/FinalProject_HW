package pages;

import data.EventData;
import exceptions.MonthException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventPage extends AbsBasePage{
    private String cardTypeSelector = ".dod_new-type__text";
    private String cardDateSelector = ".dod_new-event__date-text";
    public EventPage(WebDriver driver) {
        super(driver);
    }
    public List<String> gatherTypeOpenVebinar() {

        List<WebElement> elements = driver.findElements(By.cssSelector(cardTypeSelector));
        List<String> typeOpenVebinar = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            typeOpenVebinar.add(elements.get(i).getText());
            System.out.println(typeOpenVebinar.get(i));
        }

        return typeOpenVebinar;
    }
       public List <String> compareType() {
           List<String> typeOpenVebinar = gatherTypeOpenVebinar();
         List <String> mismatches = null;
           for (int i = 0; i < typeOpenVebinar.size(); i++) {
               if (!EventData.TYPE_EVENT.getTitle().equals(typeOpenVebinar.get(i))) {
                   mismatches.add((Integer.toString (i)));
               }
           }
        return mismatches;

       }
    public List<String> gatherDateOfCard() {

        List<WebElement> elements = driver.findElements(By.cssSelector(cardDateSelector));
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < elements.size(); i=i+2) {
            dates.add(elements.get(i).getText());

        }
        return dates;
    }
    public String convertMonthFromRussianToNumber(String month) throws MonthException {
        switch (month) {
            case "сентября" : {
                return "09";
            }
            case "октября" : {
                return "10";
            }
            case "ноября" : {
                return "11";
            }
            case "декабря" : {
                return "12";
            }
            case "января" : {
                return "01";
            }
            case "февраля" : {
                return "02";
            }
            case "марта" : {
                return "03";
            }
            case "апреля" : {
                return "04";
            }
            case "мая" : {
                return "05";
            }
            case "июня" : {
                return "06";
            }
            case "июля" : {
                return "07";
            }
            case "августа" : {
                return "08";
            }
            default:
                throw new MonthException(month);
        }
    }

    public String[] divideDateOnParts(int number){
        List <String> dates = gatherDateOfCard();
        String str = dates.get(number);
        String [] fragments  = str.split(" ");
        return fragments;
    }
    public List <String> swapDateParts() throws MonthException {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        List <String> convertDates = new ArrayList<>();
        for (int i = 0; i <gatherDateOfCard().size() ; i++) {
            String [] fragments = divideDateOnParts(i);
            if (Integer.parseInt(fragments[0])<10) {fragments[0]="0"+ fragments[0];}
            convertDates.add(year + "-"+ convertMonthFromRussianToNumber(fragments[1])+ "-"

                    + fragments[0] + "swapDateParts");
                    }
        return convertDates;
    }
    public List <Date> convertStringToDate() throws MonthException, ParseException {
        List <Date> parsedDates = new ArrayList<>();
        for (int i = 0; i < swapDateParts().size() ; i++) {
            String dateStr = swapDateParts().get(i);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = (Date) formatter.parse(dateStr);
            parsedDates.add(startDate);
            System.out.println(startDate+" convertStringToDate()");

        }

        return parsedDates;
    }
    public Boolean compareCurrentDateWithEvents() throws ParseException, MonthException {
        LocalDate now = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(now);
        Date date = new Date(sqlDate.getTime());
        List <Date> pageList = convertStringToDate();
        if (!date.before(pageList.get(0))) {
            System.out.println("Текущая дата меньше тех что на сайте.Все в порядке.");
            return true;
        }
        else System.out.println("Текущая дата больше тех что на сайте. Внимание на сайте есть просроченные мероприятия.");
        return false;
    }
}
