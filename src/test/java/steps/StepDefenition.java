package steps;

import io.cucumber.java.ru.Когда;
import managers.ManagerPages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepDefenition {
    private static final Logger log = LoggerFactory.getLogger(StepDefenition.class);
    private final ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getMainPage() {
        log.info("Cтартовая страница загружена");
        app.getMainPage();
        app.getMainPage().checkCookiesBar();
    }

    @Когда("^Выбор города доставки '(.*)'$")
    public void setupCurrentCity(String currentCityName) throws InterruptedException {
        log.info("Выбор города доставки {}", currentCityName);
        app.getMainPage().setupCurrentCity(currentCityName);
    }

    @Когда("^Переход в раздел меню '(.*)'$")
    public void getMainPageItem(String menuItemName) {
        log.info("Переход в раздел меню {}", menuItemName);
        app.getMainPage().selectMenuItem(menuItemName);
    }

    @Когда("^Выбор товара '(.*)' из списка$")
    public void getItemFromCollection(String itemName) {
        log.info("Выбор товара {} из списка", itemName);
        app.getItemCollectionPage().selectItem(itemName);
    }

    @Когда("^Добавление товара в корзину$")
    public void addItemToCart() {
        log.info("Добавление товара в корзину");
        app.getItemPage().addToCart();
    }

    @Когда("^Проверка стоимости заказа '(.*)'$")
    public void checkOrderPrice(String orderPrice) {
        log.info("Проверка стоимости заказа {}", orderPrice);
        app.getItemPage().proceedToCart().checkOrderPrice(orderPrice);
    }
}
