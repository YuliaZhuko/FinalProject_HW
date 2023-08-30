package driver;

import driver.impl.ChromeWebDriver;
import exceptions.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory  implements  IDriverFactory{

    private String browserType = System.getProperty("browser").toLowerCase();
    private String LOGIN = System.getProperty("site.login");
    private String PASSWORD = System.getProperty("site.password");



    @Override
    public WebDriver getDriver() throws DriverNotSupportedException {
        switch (this.browserType) {
            case "chrome" : {
                return new ChromeWebDriver().newDriver();
            }
            default:
                throw new DriverNotSupportedException(this.browserType);
        }
    }
}
