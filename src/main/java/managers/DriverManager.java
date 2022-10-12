package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static utils.PropsConstant.*;

public class DriverManager {

    private static WebDriver driver;
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    private DriverManager() {
    }

    private static void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(PATH_GEKO_DRIVER));
                FirefoxOptions geckoOptions = new FirefoxOptions();
                geckoOptions.setProfile(new FirefoxProfile());
                geckoOptions.addPreference("dom.webnotifications.enabled", false);
                driver = new FirefoxDriver(geckoOptions);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                ChromeOptions remoteChromeOptions = new ChromeOptions();
                remoteChromeOptions.addArguments("--disable-notifications");
                capabilities.setCapability(ChromeOptions.CAPABILITY, remoteChromeOptions);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://130.193.49.85:4444/wd/hub").toURL(),
                            capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }
    }

    public static WebDriver getDriver(){
        if (driver == null){
            initDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        getDriver().quit();
        driver = null;
    }
}
