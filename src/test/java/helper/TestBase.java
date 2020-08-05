package helper;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            Reporter.log("=====Chrome Browser Session Started=====", true);
            WebDriverManager.chromedriver().setup();
            //String chromePath = System.getProperty("user.dir") + "/Resources/chromedriver";
            //System.setProperty("webdriver.chrome.driver", chromePath);

            //to download file in Downloads file
            String downloadFilepath = System.getProperty("user.dir") + "/Downloads";
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFilepath);
            options.setExperimentalOption("prefs", chromePrefs);
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(cap);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            Reporter.log("=====FireFox Browser Session Started=====", true);
            WebDriverManager.firefoxdriver().setup();
            //String firefoxPath = System.getProperty("user.dir") + "/Resources/geckodriver";
            //System.setProperty("webdriver.gecko.driver", firefoxPath);
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            Reporter.log("=====Safari Browser Session Started=====", true);
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("headless")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        Reporter.log("=====Application Started=====", true);

    }

    @AfterSuite
    public void stopDriver() {
        driver.quit();
        Reporter.log("=====Browser Session End=====", true);
    }


}
