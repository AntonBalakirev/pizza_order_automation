package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ItemCollectionPage extends BasePage{

    @FindBy(xpath = "//div[@class='catalog-item__title']")
    List<WebElement> itemCollection;

    public BasePage selectItem(String itemName) {
        for (WebElement item : itemCollection) {
            if (item.getText().equalsIgnoreCase(itemName)) {
                scrollToElementBottom(item);
                WebElement itemLink = item.findElement(By.xpath("ancestor::a"));
                action.moveToElement(itemLink).click().build().perform();
                return app.getItemPage();
            }
        }
        return this;
    }
}
