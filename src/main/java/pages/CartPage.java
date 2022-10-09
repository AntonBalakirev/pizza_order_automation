package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@class='basket__total-price-wrap']/p")
    WebElement orderPriceField;

    public void checkOrderPrice(String orderPrice) {
        assertTrue(
                "Стоимость товаров неверно отображается в корзине",
                elementToBeVisible(orderPriceField).getText().contains(orderPrice)
        );
    }
}
