package inetum.questions;

import inetum.interactions.WaitInteraction;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.Actor;

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
}
