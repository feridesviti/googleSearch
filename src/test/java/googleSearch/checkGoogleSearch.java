package googleSearch;

import googlePage.IssuePage;
import googlePage.SearchPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class checkGoogleSearch {
    private static WebDriver driver;
    private static String request;
    private static SearchPage searchPage;
    private static IssuePage issuePage;

    @Before
    public void before() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        issuePage = PageFactory.initElements(driver, IssuePage.class);
    }

    public checkGoogleSearch(String request) {
        this.request = request;
    }

    @Parameterized.Parameters
    public static List<String> data() {
        List<String> data = new ArrayList<String>();
        data.add("automation");
        data.add("selenium");
        data.add("testautomationday");
        return data;
    }

    @Test
    public void checkThatTitleContainsSearchedWord() throws InterruptedException {
        driver.get("https://www.google.com");
        searchPage.inputToSearchField(request);
        List<WebElement> issueReferences = issuePage.getIssueReferences();
        String href = issueReferences.get(0).getAttribute("href");
        driver.get(href);
        String title = driver.getTitle();
        assertThat(title).as(href + " title should contain 'automation'").containsIgnoringCase(request);
    }

    @Test
    public void checkSearchResultForContainsExpectedDomain() throws InterruptedException {
        driver.get("https://www.google.com");
        searchPage.inputToSearchField(request);
        List<String> hrefList = getSearchResultsList(5);
        boolean containsExpectedDomain = false;
        for (String href : hrefList) {
            containsExpectedDomain = href.contains("testautomationday.com");
            if (containsExpectedDomain) {
                break;
            }
        }
        assertThat(containsExpectedDomain).as("Search result does not contains domain 'testautomationday.com'").isEqualTo(true);
    }

    private List<String> getSearchResultsList(int numberOfSearchPages) throws InterruptedException {
        List<String> hrefList = new ArrayList<String>();
        do {
            List<WebElement> issueReferences = issuePage.getIssueReferences();
            for (WebElement issueReference : issueReferences) {
                hrefList.add(issueReference.getAttribute("href"));
            }
            issuePage.clickOnNextPage();
            sleep(500);
        } while (issuePage.getActivePageNumber() <= numberOfSearchPages);
        return hrefList;
    }

    @After
    public void after() {
        driver.quit();
    }

}
