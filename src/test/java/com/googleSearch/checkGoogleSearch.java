package com.googleSearch;

import com.base.BaseTest;
import com.googlePage.SearchPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class checkGoogleSearch extends BaseTest {
    private String request;
    private SearchPage searchPage = new SearchPage(eventDriver);

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
    public void checkThatTitleContainsSearchedWord() {
        eventDriver.get("https://www.google.com");
        List<String> issueReferences = searchPage.inputToSearchField(request)
                .confirmRequest()
                .getSearchResultsList(1);
        String href = issueReferences.get(0);
        eventDriver.get(href);
        String title = eventDriver.getTitle();
        assertThat(title).as(href + " title should contain '" + request + "'").containsIgnoringCase(request);
    }

    @Test
    public void checkSearchResultForContainsExpectedDomain(){
        eventDriver.get("https://www.google.com");
        List<String> hrefList = searchPage.inputToSearchField(request)
                .confirmRequest()
                .getSearchResultsList(5);
        boolean containsExpectedDomain = false;
        for (String href : hrefList) {
            containsExpectedDomain = href.contains(request + ".com");
            if (containsExpectedDomain) {
                break;
            }
        }
        assertThat(containsExpectedDomain).as("Search result does not contains domain '" + request + ".com'").isEqualTo(true);
    }


}
