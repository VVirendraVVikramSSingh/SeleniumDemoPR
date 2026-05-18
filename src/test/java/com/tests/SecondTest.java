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
        // CI FIX: Jenkins runs in the background and cannot open physical UI windows.
        // Adding 'headless' mode ensures it runs seamlessly inside Jenkins server environments.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        // Step 1: Open Google directly
        driver.get("https://www.google.com");
        
        // Step 2: Locate the search bar using its 'name' attribute
        WebElement searchBox = driver.findElement(By.name("q"));
        
        // Step 3: Type 'Jenkins CI' and press the Enter key
        searchBox.sendKeys("Jenkins CI");
        searchBox.sendKeys(Keys.ENTER);
        
        // CI FIX: Wait up to 10 seconds for the page title to actually update with our search term
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Jenkins CI"));
        
        // Step 4: Verify that the search results page loaded
        String pageTitle = driver.getTitle();
        System.out.println("Search Results Page Title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("Jenkins CI"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}