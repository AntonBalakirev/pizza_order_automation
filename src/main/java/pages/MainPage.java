package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {
    @FindBy(xpath = "//button[@onclick='hideInfo()']")
    WebElement acceptCookieButton;

    @FindBy(xpath = "//nav//div[@class='city-confirm__title']")
    WebElement cityConfirmationTitle;

    @FindBy(xpath = "//nav//button[@class='city-confirm__btn city-confirm__btn_confirm']")
    WebElement cityConfirmationAgreedButton;

    @FindBy(xpath = "//nav//button[@class='city-confirm__btn']")
    WebElement cityConfirmationToggleButton;

    @FindBy(xpath = "//nav//div[@class='wrapped-in-button-desktop']//a[@class='select-city__item']")
    List<WebElement> cityList;

    @FindBy(xpath = "//a[@class='main-nav__link']")
    List<WebElement> menuList;

    public BasePage selectMenuItem(String menuItemName) {
        for (WebElement menuItem : menuList) {
            if (menuItem.getText().equalsIgnoreCase(menuItemName)) {
                action.moveToElement(menuItem).click().build().perform();
                return app.getItemCollectionPage();
            }
        }
        Assert.fail("Раздел меню '" + menuItemName + "' не был найден на стартовой странице!");
        return this;
    }

    public MainPage checkCookiesBar() {
        if (acceptCookieButton.isDisplayed()) {
            action.moveToElement(acceptCookieButton).click().build().perform();
        }
        return this;
    }

    public MainPage setupCurrentCity(String currentCityName) throws InterruptedException {
        if (cityConfirmationTitle.isDisplayed()) {
            if (cityConfirmationTitle.getText().contains(currentCityName)) {
                action.moveToElement(cityConfirmationAgreedButton).click().build().perform();
            } else {
                action.moveToElement(cityConfirmationToggleButton).pause(2000).build().perform();
                for (WebElement menuItem : cityList) {
                    if (menuItem.getText().equalsIgnoreCase(currentCityName)) {
                        action.moveToElement(menuItem).click().build().perform();
                    }
                }
                Thread.sleep(5000);
                elementToBeClickable(cityConfirmationAgreedButton);
                Assert.assertTrue(
                        "City was not chosen",
                        cityConfirmationTitle.getText().contains(currentCityName)
                );
                action.moveToElement(cityConfirmationAgreedButton).click().build().perform();
            }
            elementToBeInvisible(cityConfirmationTitle);
            System.out.println("City was selected");
            hideInfo();
            return this;
        }
        System.out.println("City selection was not happened");
        return this;
    }
}
