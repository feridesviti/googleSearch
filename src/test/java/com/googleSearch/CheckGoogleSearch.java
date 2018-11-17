package com.googleSearch;

import com.base.BaseTest;
import com.googlePage.SearchPage;
import com.helper.Parallelized;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Parallelized.class)
public class CheckGoogleSearch extends BaseTest {
    private String request;
    private SearchPage searchPage;

    public CheckGoogleSearch(String request) {
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
        searchPage = new SearchPage(eventDriver);
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
        searchPage = new SearchPage(eventDriver);
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
