package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver(); // Selenium 4 automatically handles driver executables
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("First Test Title: " + title);
        Assert.assertTrue(title.contains("Google"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}