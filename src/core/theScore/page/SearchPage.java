package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class SearchPage extends AbstractAppPage {

    public static String topNavigationPanel = "//*[@resource-id='com.fivemobile.thescore:id/tabLayout']";
    public static String backButton = "//*[@content-desc='Navigate up']";
    public static String searchBar = "//*[@resource-id='com.fivemobile.thescore:id/search_src_text']";
    public static String resultList = "//*[@resource-id='com.fivemobile.thescore:id/recyclerView']";
    public static String labelText = "//*[@text='%s']";
    public static String allLabel = "ALL";
    public static String searchResultText = "//android.widget.TextView[@text='%s']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(topNavigationPanel)) {
            Assert.assertTrue(waitUntilElementIsPresent(topNavigationPanel).size() > 0, "Top navigation panel is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(backButton).size() > 0, "Back button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(searchBar).size() > 0, "Search bar is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(resultList).size() > 0, "Search results list not loaded");
        }
    }

    public void enterTextInSearchBar(String text) {
        waitUntilElementIsPresent(searchBar);
        writeToMobileField(text, searchBar);
    }

    public void clickOnSearchResult(String text) {
        String xpath = String.format(searchResultText, text);
        waitUntilElementIsPresent(xpath);
        clickWhenClickable(xpath);
    }

    public void clickAllSearchResults() {
        String xpath = String.format(labelText, allLabel);
        waitUntilElementIsPresent(xpath);
        clickWhenClickable(xpath);
    }
}
