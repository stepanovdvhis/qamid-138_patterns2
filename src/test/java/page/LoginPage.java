package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {
    private SelenideElement loginField = $("input[name='login']");
    private SelenideElement passwordField = $("input[name='password']");
    // Ищем любую кнопку на странице (обычно она одна)
    private SelenideElement submitButton = $("button");

    public void login(String login, String password) {
        // Ждем, пока появится кнопка
        submitButton.shouldBe(visible);

        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
    }
}