package pages;

import managers.ManagerPages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static managers.DriverManager.getDriver;
import static managers.TestPropManager.getTestPropManager;
import static utils.PropsConstant.EXPLICITLY_WAIT_LIMIT;
import static utils.PropsConstant.EXPLICITLY_WAIT_POLLING_TIMEOUT;

public class BasePage {
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    protected ManagerPages app = ManagerPages.getManagerPages();
    protected Actions action = new Actions(getDriver());
    protected JavascriptExecutor jexecutor = (JavascriptExecutor) getDriver();
    protected WebDriverWait wait = new WebDriverWait(
            getDriver(),
            Integer.parseInt(getTestPropManager().getProperty(EXPLICITLY_WAIT_LIMIT)),
            Integer.parseInt(getTestPropManager().getProperty(EXPLICITLY_WAIT_POLLING_TIMEOUT))
    );

    @FindBy(xpath = "//button[@onClick='hideInfo()']")
    WebElement infoButton;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
        hideInfo();
    }

    protected void scrollToElementTop(WebElement element) {
        log.debug("Execute scroll to element top: {}", element.toString());
        jexecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollToElementBottom(WebElement element) {
        log.debug("Execute scroll to element top: {}", element.toString());
        jexecutor.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    protected WebElement elementToBeClickable(WebElement element) {
        log.debug("Execute wait until element will be clickable: {}", element.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement elementToBeVisible(WebElement element) {
        log.debug("Execute wait until element will be visible: {}", element.toString());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void elementToBeInvisible(WebElement element) {
        log.debug("Execute wait until element will be invisible: {}", element.toString());
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected BasePage hideInfo(){
        if(infoButton.isDisplayed()){
            action.moveToElement(infoButton).click().build().perform();
        }
        return this;
    }
}
