import com.codeborne.selenide.Configuration;
import data.AuthApi;
import data.DataGenerator;
import data.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void setUp() {
        Configuration.browser = "firefox";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithActiveUser() {
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        com.codeborne.selenide.Selenide.sleep(2000);
    }
}