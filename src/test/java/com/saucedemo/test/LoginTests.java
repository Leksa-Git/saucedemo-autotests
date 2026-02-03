package com.saucedemo.test;

import com.saucedemo.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private final String URL = "https://www.saucedemo.com/";


    @Test
    public void successfulLoginTest() {
        login("standard_user", "secret_sauce");


        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }


    @Test
    public void wrongPasswordTest() {
        login("standard_user", "wrong_password");

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        assertTrue(error.isDisplayed());
        assertEquals("Epic sadface: Username and password do not match any user in this service", error.getText());
    }


    @Test
    public void lockedOutUserTest() {
        login("locked_out_user", "secret_sauce");

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        assertTrue(error.isDisplayed());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", error.getText());
    }

    // 4️⃣ Пустые поля
    @Test
    public void emptyFieldsTest() {
        login("", "");

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        assertTrue(error.isDisplayed());
        assertEquals("Epic sadface: Username is required", error.getText());
    }


    @Test
    public void performanceGlitchUserTest() {
        login("performance_glitch_user", "secret_sauce");


        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }


    private void login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
