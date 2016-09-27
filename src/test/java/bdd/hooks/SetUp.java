package bdd.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;

public class SetUp {

    public static WebDriver driver;
    public static Scenario scenario;
    public static String URL = System.getProperty("url");
    private String BROWSER = System.getProperty("browser");
    private String OS = System.getProperty("os.name").toLowerCase();
    private String CHROME_PATH = "src/test/resources/chromedriver/";
    private String APP_URL = "https://weather-acceptance.herokuapp.com/";


    @Before
    public void before(Scenario scenario) throws InterruptedException, MalformedURLException {
        SetUp.scenario = scenario;
        setBrowser();
        setUrl();
    }

    private void setBrowser() throws MalformedURLException {
        if ((BROWSER == null) || BROWSER.equalsIgnoreCase("Chrome")) {
            if (OS.contains("windows")) {
                CHROME_PATH = CHROME_PATH + "windows/chromedriver.exe";
            } else if (OS.contains("linux")) {
                CHROME_PATH = CHROME_PATH + "linux/chromedriver";
            }
            System.setProperty("webdriver.chrome.driver", CHROME_PATH);
            driver = new ChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
    }


    private void setUrl() {
        if (URL == null) {
            URL = APP_URL;
        }

    }

    @After
    public void tearDown(Scenario scenario) {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}