package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class SecondTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://www.google.com");
        
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Jenkins CI");
        searchBox.sendKeys(Keys.ENTER);
        
        // FIX: Instead of waiting for a flaky title, wait up to 10 seconds 
        // for the main search results container div ("#search") to appear on screen.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        
        // Verify that the search results wrapper element is actually displayed
        Assert.assertTrue(searchResults.isDisplayed(), "Search results failed to appear!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}