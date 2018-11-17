package com.base;

import com.helper.WebDriverLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public EventFiringWebDriver eventDriver;
    private WebDriverLogger webDriverLogger;

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        eventDriver = new EventFiringWebDriver(driver);
        webDriverLogger = new WebDriverLogger();
        eventDriver.register(webDriverLogger);
    }

    @After
    public void after(){
        eventDriver.unregister(webDriverLogger);
        driver.quit();
        eventDriver.quit();
    }
}
