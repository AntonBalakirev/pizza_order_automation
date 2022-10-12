package steps;

import io.cucumber.java.ru.Когда;
import managers.ManagerPages;

public class StepDefenition {
    private final ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getMainPage() {
        app.getMainPage();
        app.getMainPage().checkCookiesBar();
    }

    @Когда("^Выбор города доставки '(.*)'$")
    public void setupCurrentCity(String currentCityName) throws InterruptedException {
        app.getMainPage().setupCurrentCity(currentCityName);
    }

    @Когда("^Переход в раздел меню '(.*)'$")
    public void getMainPageItem(String menuItemName) {
        app.getMainPage().selectMenuItem(menuItemName);
    }

    @Когда("^Выбор товара '(.*)' из списка$")
    public void getItemFromCollection(String itemName) {
        app.getItemCollectionPage().selectItem(itemName);
    }

    @Когда("^Добавление товара в корзину$")
    public void addItemToCart() {
        app.getItemPage().addToCart();
    }

    @Когда("^Проверка стоимости заказа '(.*)'$")
    public void checkOrderPrice(String orderPrice) {
        app.getItemPage().proceedToCart().checkOrderPrice(orderPrice);
    }
}
