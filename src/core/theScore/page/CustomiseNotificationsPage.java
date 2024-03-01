package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class CustomiseNotificationsPage extends AbstractAppPage {

    public static String neverMissAGameBanner = "//*[@resource-id='com.fivemobile.thescore:id/image_onboarding']";
    public static String neverMissAGameTitle = "//*[@text='Never miss a game']";
    public static String notificationsOptions = "//*[@resource-id='com.fivemobile.thescore:id/pullToRefresh']";
    public static String doneButton = "//*[@resource-id='com.fivemobile.thescore:id/action_button_text']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(neverMissAGameBanner)) {
            Assert.assertTrue(waitUntilElementIsPresent(neverMissAGameBanner).size() > 0, "Never miss a game banner is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(neverMissAGameTitle).size() > 0, "Never miss a game title is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(doneButton).size() > 0, "Done button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(notificationsOptions).size() > 0, "Notifications options is not loaded");
        }
    }

    public void clickDoneButton() {
        waitUntilElementIsPresent(doneButton);
        clickWhenClickable(doneButton);
    }
}