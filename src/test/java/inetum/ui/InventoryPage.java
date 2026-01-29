package inetum.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoryPage {

    public static final Target INVENTORY_PAGE_TITLE = Target.the("título de página de inventario")
            .located(By.cssSelector("span.title"));

    public static final Target PRODUCTS_INVENTORY = Target.the("inventario de productos")
            .located(By.cssSelector("div.inventory_list"));

    public static final Target PRODUCT_ITEMS = Target.the("items de productos")
            .located(By.cssSelector("div[data-test='inventory-item']"));

    public static Target getProductNameByIndex(int index) {
        return Target.the("nombre del producto en posición " + index)
                .locatedBy("(//div[@data-test='inventory-item'])[" + (index + 1) + "]//div[@data-test='inventory-item-name']");
    }

    public static Target getProductPriceByIndex(int index) {
        return Target.the("precio del producto en posición " + index)
                .locatedBy("(//div[@data-test='inventory-item'])[" + (index + 1) + "]//div[@data-test='inventory-item-price']");
    }

    public static Target getProductAddButton(String productName) {
        return Target.the("Botón del producto: " + productName)
                .located(By.id("add-to-cart-{0}".replace("{0}", productName.toLowerCase().replace(" ", "-")))
                );
    }

    public static Target getProductPrice(String productName) {
        return Target.the("Precio del producto: " + productName)
                .locatedBy("//div[@data-test='inventory-item-name' and normalize-space(text())='{0}']/ancestor::div[@data-test='inventory-item']//div[@data-test='inventory-item-price']"
                        .replace("{0}", productName));
    }

    public static Target getCartProduct(String productName) {
        return Target.the("Product en el carrito: " + productName)
                .locatedBy("//div[@class='inventory_item_name' and normalize-space(text())='{0}']"
                        .replace("{0}", productName));
    }

    public static Target getAllProductItems() {
        return Target.the("todos los items de productos")
                .located(By.cssSelector("div[data-test='inventory-item']"));
    }
}
