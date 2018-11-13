package com.helper;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.apache.log4j.Logger;

public class WebDriverLogger extends AbstractWebDriverEventListener {

    private static final Logger Log = Logger.getLogger(WebDriverLogger.class.getName());
    public WebDriverLogger(){
        String log4jConfPath = "src\\test\\java\\resources\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        Log.info("WebDriver navigated to '" + url + "'");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        Log.info("WebDriver after click on element - " + elementDescription(element));
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.info("WebDriver before click on element - " + elementDescription(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        Log.info("WebDriver set value  " + keysToSend[0]);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        Log.info("WebDriver value is  " + keysToSend[0]);
    }

    private String elementDescription(WebElement element) {
        if (element == null){
            return "";
        }

        try {
            return " id: " + element.getAttribute("id");
        }catch (StaleElementReferenceException e){
        }

        try {
            return " name: " + element.getAttribute("name");
        }catch (StaleElementReferenceException e){

        }

        try {
            return " class: " + element.getClass();
        }catch (StaleElementReferenceException e){

        }

        try {
            return " name: " + element.getText();
        }catch (StaleElementReferenceException e){

        }

        return "";
    }
}
