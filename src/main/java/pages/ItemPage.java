package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage {

    @FindBy(xpath = "//button[@class='add-to-cart__button']")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[@class='cart-proceed modal-toggle catalog-cart__button']")
    WebElement cartButton;

    public ItemPage addToCart(){
        action.moveToElement(elementToBeClickable(addToCartButton)).click().build().perform();
        return this;
    }

    public CartPage proceedToCart(){
        action.moveToElement(elementToBeClickable(cartButton)).click().build().perform();
        return app.getCartPage();
    }

}
