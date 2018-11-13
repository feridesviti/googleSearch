package com.base;

import com.helper.WebDriverLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    protected static EventFiringWebDriver eventDriver;
    private static WebDriverLogger webDriverLogger;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        eventDriver = new EventFiringWebDriver(driver);
        webDriverLogger = new WebDriverLogger();
        eventDriver.register(webDriverLogger);
    }

    @AfterClass
    public static void afterClass(){
        eventDriver.unregister(webDriverLogger);
        eventDriver.quit();
    }
}
