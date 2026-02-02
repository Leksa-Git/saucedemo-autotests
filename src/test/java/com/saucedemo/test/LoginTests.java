package com.saucedemo.test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.WaitUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Авторизация") // add 2nd random comment
@Feature("Логин")
public class LoginTests extends BaseTest {

    @Test
    @Story("Успешный логин")
    void successfulLogin() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        assertTrue(inventory.isOpened());
    }

    @Test
    void loginWithWrongPassword() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "wrong_pass");

        assertTrue(login.getErrorText().contains("Username and password do not match"));
    }

    @Test
    void loginLockedUser() {
        LoginPage login = new LoginPage(driver);
        login.login("locked_out_user", "secret_sauce");

        assertTrue(login.getErrorText().contains("locked out"));
    }

    @Test
    void loginWithEmptyFields() {
        LoginPage login = new LoginPage(driver);
        login.login("", "");

        assertTrue(login.getErrorText().contains("Username is required"));
    }

    @Test
    void performanceGlitchUserLogin() {
        LoginPage login = new LoginPage(driver);
        login.login("performance_glitch_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        WaitUtils.waitForVisibility(driver, 10, inventory);

        assertTrue(inventory.isOpened());
    }
}
