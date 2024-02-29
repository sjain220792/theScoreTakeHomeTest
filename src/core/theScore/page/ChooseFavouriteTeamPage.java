package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class ChooseFavouriteTeamPage extends AbstractAppPage {

    public static String chooseFavTeamText = "//*[@resource-id='com.fivemobile.thescore:id/title_onboarding']";
    public static String favouritesIcon = "//*[@resource-id='com.fivemobile.thescore:id/icon']";
    public static String searchBar = "//*[@resource-id='com.fivemobile.thescore:id/search_bar_placeholder']";
    public static String teamTable = "//*[@resource-id='com.fivemobile.thescore:id/viewPager']";
    public static String continueButton = "//*[@resource-id='com.fivemobile.thescore:id/action_button_text']";
    public static String textLocator = "//*[@text='%s']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(favouritesIcon)) {
            Assert.assertTrue(waitUntilElementIsPresent(chooseFavTeamText).size() > 0, "Choose favourite team text is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(favouritesIcon).size() > 0, "Favourites icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(teamTable).size() > 0, "Team table not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(continueButton).size() > 0, "Continue button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(searchBar).size() > 0, "Search bar is not loaded");
        }
    }

    public void clickContinueButton() {
        waitUntilElementIsPresent(continueButton);
        clickWhenClickable(continueButton);
    }

    public void selectFavTeam(String team) {
        String xpath = String.format(textLocator, team);
        clickWhenClickable(xpath);
    }
}