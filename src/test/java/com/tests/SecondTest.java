package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecondTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        // Step 1: Navigate to Google
        driver.get("https://www.google.com");
        
        // Step 2: Search for the term
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Jenkins CI");
        searchBox.sendKeys(Keys.ENTER);
        
        // Step 3: Print verification logs to the Jenkins Console Output
        System.out.println("Current Page URL after search: " + driver.getCurrentUrl());
        System.out.println("Current Page Title after search: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}