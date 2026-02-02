package com.saucedemo.utils;

import com.saucedemo.pages.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitForVisibility(WebDriver driver, int seconds, InventoryPage page) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(d -> page.isOpened());
    }
}
