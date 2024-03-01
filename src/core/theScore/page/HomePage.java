package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class HomePage extends AbstractAppPage {

    public static String bottomNavigationPanel = "//*[@resource-id='com.fivemobile.thescore:id/bottom_navigation']";
    public static String messageIcon = "//*[@resource-id='com.fivemobile.thescore:id/message_icon']";
    public static String searchBar = "//*[@resource-id='com.fivemobile.thescore:id/search_bar_text_view']";
    public static String userIcon = "//*[@class='android.widget.ImageButton']";
    public static String theScoreBetLogo = "//*[@resource-id='com.fivemobile.thescore:id/sportsbook_logo']";
    public static String xCloseButton = "//*[@resource-id='com.fivemobile.thescore:id/dismiss_modal']";
    public static String labelText = "//*[@text='%s']";
    public static String allLabel = "ALL";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(userIcon)) {
            Assert.assertTrue(waitUntilElementIsPresent(bottomNavigationPanel).size() > 0, "Bottom navigation panel is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(messageIcon).size() > 0, "Message Icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(searchBar).size() > 0, "Home search bar is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(userIcon).size() > 0, "User icon not loaded");
        }
    }

    public boolean isTheScoreBetPopUpPresent() {
        return isMobileElementPresent(theScoreBetLogo);
    }

    public void clickXCloseButton() {
        waitUntilElementIsPresent(xCloseButton);
        clickWhenClickable(xCloseButton);
    }

    public void clickOnSearchBar() {
        waitForLoadingToFinish();
        waitUntilElementIsPresent(searchBar);
        clickWhenClickable(searchBar);
    }
}
