package managers;

import java.util.concurrent.TimeUnit;

import static managers.DriverManager.getDriver;
import static managers.DriverManager.quitDriver;
import static managers.ManagerPages.disableManagerPages;
import static managers.TestPropManager.getTestPropManager;
import static utils.PropsConstant.*;

public class InitManager {

    private InitManager(){
        throw new IllegalStateException("Utility class");
    }

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(
                Integer.parseInt(getTestPropManager().getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(
                Integer.parseInt(getTestPropManager().getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        getDriver().get(getTestPropManager().getProperty(APP_URL));
    }

    public static void quitFramework() {
        getDriver().manage().deleteAllCookies();
        quitDriver();
        disableManagerPages();
    }
}
