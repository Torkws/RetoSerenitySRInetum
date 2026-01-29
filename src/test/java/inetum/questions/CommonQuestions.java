package inetum.questions;

import inetum.interactions.WaitInteraction;
import inetum.models.CarritoInfo;
import inetum.ui.InventoryPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.Actor;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class CommonQuestions implements Question<Boolean> {

    private String questionDescription;

    private CommonQuestions(String description) {
        this.questionDescription = description;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return false;
    }

    @Override
    public String toString() {
        return questionDescription;
    }

    public static Question<Boolean> checkIfObjectIsDisplayed(Target target) {
        return Question.about("✓ Verificar que el elemento está visible: " + target.getName())
                .answeredBy(actor -> {
                    actor.attemptsTo(
                            WaitInteraction.forElementToBeVisible(target)
                    );
                    try {
                        return the(target)
                                .answeredBy(actor)
                                .isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                });
    }

    public static Question<Boolean> checkIfObjectIsDisplayed(Target target, String message) {
        return Question.about("✓ " + message)
                .answeredBy(actor -> {
                    actor.attemptsTo(
                            WaitInteraction.forElementToBeVisible(target)
                    );
                    try {
                        return the(target)
                                .answeredBy(actor)
                                .isVisible();
                    } catch (Exception e) {
                        return false;
                    }
                });
    }

    public static Question<String> getObjectText(Target target) {
        return Question.about("✓ Obtener el texto del elemento: " + target.getName())
                .answeredBy(actor -> {
                    actor.attemptsTo(
                            WaitInteraction.forElementToBeVisible(target)
                    );
                    try {
                        return the(target)
                                .answeredBy(actor)
                                .getText();
                    } catch (Exception e) {
                        return "";
                    }
                });
    }

    public static Question<String> getObjectText(Target target, String message) {
        return Question.about("✓ " + message)
                .answeredBy(actor -> {
                    actor.attemptsTo(
                            WaitInteraction.forElementToBeVisible(target)
                    );
                    try {
                        return the(target)
                                .answeredBy(actor)
                                .getText();
                    } catch (Exception e) {
                        return "";
                    }
                });
    }

    public static Question<List<CarritoInfo.ProductoCarrito>> getProductsNameAndPrice(Target productsTarget) {
        return Question.about("✓ Obtener lista de productos con nombre y precio")
                .answeredBy(actor -> {
                    actor.attemptsTo(
                            WaitInteraction.forElementToBeVisible(productsTarget)
                    );

                    List<CarritoInfo.ProductoCarrito> productos = new ArrayList<>();

                    // Iterar a través de los productos por índice hasta que no encuentre más
                    int index = 0;
                    boolean hasMoreProducts = true;

                    while (hasMoreProducts) {
                        try {
                            // Usar los métodos de InventoryPage para obtener los targets
                            Target currentProductNameTarget = InventoryPage.getProductNameByIndex(index);
                            Target currentProductPriceTarget = InventoryPage.getProductPriceByIndex(index);

                            // Obtener el texto del nombre y precio usando Serenity
                            String productName = the(currentProductNameTarget).answeredBy(actor).getText();
                            String priceText = the(currentProductPriceTarget).answeredBy(actor).getText();

                            // Convertir el precio a double
                            double price = Double.parseDouble(priceText.replace("$", "").trim());

                            // Agregar a la lista
                            productos.add(new CarritoInfo.ProductoCarrito(productName, price));

                            index++;
                        } catch (Exception e) {
                            // Si no encuentra el elemento, significa que no hay más productos
                            hasMoreProducts = false;
                        }
                    }

                    return productos;
                });
    }

}
