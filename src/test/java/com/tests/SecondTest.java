package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecondTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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