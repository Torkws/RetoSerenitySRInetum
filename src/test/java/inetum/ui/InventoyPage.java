package inetum.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class InventoyPage {

    public static final Target INVENTORY_PAGE_TITLE = Target.the("título de página de inventario")
            .located(By.cssSelector("span.title"));

    public static final Target PRODUCTS_INVENTORY = Target.the("inventario de productos")
            .located(By.cssSelector("div.inventory_list"));

}
