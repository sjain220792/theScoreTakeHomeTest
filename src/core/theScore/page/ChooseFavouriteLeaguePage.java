package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class ChooseFavouriteLeaguePage extends AbstractAppPage {

    public static String chooseFavLeagueText = "//*[@resource-id='com.fivemobile.thescore:id/title_onboarding']";
    public static String favouritesIcon = "//*[@resource-id='com.fivemobile.thescore:id/icon']";
    public static String leaguesTable = "//*[@resource-id='com.fivemobile.thescore:id/recyclerView']";
    public static String continueButton = "//*[@resource-id='com.fivemobile.thescore:id/action_button_text']";
    public static String imageLocation = "//*[@resource-id='com.fivemobile.thescore:id/img_location']";
    public static String maybeLaterButton = "//*[@resource-id='com.fivemobile.thescore:id/btn_disallow']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(favouritesIcon)) {
            Assert.assertTrue(waitUntilElementIsPresent(chooseFavLeagueText).size() > 0, "Choose favourite league text is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(favouritesIcon).size() > 0, "Favourites Icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(leaguesTable).size() > 0, "Leagues table is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(continueButton).size() > 0, "Continue button is not loaded");
        }
    }

    public void clickContinueButton() {
        waitUntilElementIsPresent(continueButton);
        clickWhenClickable(continueButton);
    }

    public void clickMayLaterForLocation() {
        waitUntilElementIsPresent(imageLocation);
        clickWhenClickable(maybeLaterButton);
    }

    public boolean isLocationPopUpPresent() {
        return isMobileElementPresent(imageLocation);
    }
}