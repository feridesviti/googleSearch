package googlePage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.*;

public class SearchPage {
    @FindBy(css = ("[name ='q']"))
    private WebElement searchField;

    public SearchPage inputToSearchField(String request) throws InterruptedException {
        searchField.sendKeys(request);
        sleep(2000);
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

}
