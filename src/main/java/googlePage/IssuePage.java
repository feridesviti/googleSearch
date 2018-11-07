package googlePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IssuePage {
    @FindBy(xpath = "//h3[@class='LC20lb']/..")
    private List<WebElement> issueReference;

    @FindBy(css = "#hdtb-msb-vis")
    private WebElement navigationBar;

    @FindBy(css = "#pnnext > span:nth-child(2)")
    private WebElement nextPageButton;

    @FindBy(css = "#nav td.cur")
    private WebElement activePageNumberElement;

    public List<WebElement> getIssueReferences(){
        return issueReference;
    }

    public IssuePage clickOnNextPage(){
        nextPageButton.click();
        return this;
    }

    public int getActivePageNumber(){
        return Integer.valueOf(activePageNumberElement.getText());
    }
}
