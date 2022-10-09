package managers;

import pages.CartPage;
import pages.ItemCollectionPage;
import pages.ItemPage;
import pages.MainPage;

public class ManagerPages {

    private static ManagerPages managerPages;
    private MainPage mainPage;
    private ItemCollectionPage itemCollectionPage;
    private ItemPage itemPage;
    private CartPage cartPage;

    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public static void disableManagerPages() {
        managerPages = null;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public ItemCollectionPage getItemCollectionPage() {
        if (itemCollectionPage == null) {
            itemCollectionPage = new ItemCollectionPage();
        }
        return itemCollectionPage;
    }

    public ItemPage getItemPage() {
        if (itemPage == null) {
            itemPage = new ItemPage();
        }
        return itemPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }
}
