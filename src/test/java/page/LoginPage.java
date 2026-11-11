package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import java.time.Duration;

public class LoginPage {
    private SelenideElement loginField = $("input[name='login']");
    private SelenideElement passwordField = $("input[name='password']");
    private SelenideElement submitButton = $("button[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void login(String login, String password) {
        loginField.shouldBe(visible, Duration.ofSeconds(10)).setValue(login);
        passwordField.shouldBe(visible, Duration.ofSeconds(10)).setValue(password);
        submitButton.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void shouldSeeError(String expectedText) {
        errorNotification.shouldBe(visible, Duration.ofSeconds(10))
                .shouldHave(text(expectedText));
    }
}