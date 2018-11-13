package com.googlePage;

import com.helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PageObject {
    private WebDriverWait wait = new WebDriverWait(driver, 90);
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ("[name ='q']"))
    private WebElement searchField;

    @FindBy(css = ("div.sbdd_b"))
    private WebElement issueResult;

    public SearchPage inputToSearchField(String request){
        searchField.sendKeys(request);
        return this;
    }

    public IssuePage confirmRequest(){
        searchField.sendKeys(Keys.ENTER);
        return new IssuePage(driver);
    }

}
