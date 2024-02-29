package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class AppLoadingPage extends AbstractAppPage {

    public static String welcomeIcon = "//*[@resource-id='com.fivemobile.thescore:id/icon_welcome']";
    public static String welcomeText = "//*[@resource-id='com.fivemobile.thescore:id/txt_welcome']";
    public static String getStartedButton = "//*[@resource-id='com.fivemobile.thescore:id/action_button_text']";
    public static String loginIn = "//*[@resource-id='com.fivemobile.thescore:id/txt_sign_in']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(welcomeIcon)) {
            Assert.assertTrue(waitUntilElementIsPresent(welcomeIcon).size() > 0, "Welcome Icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(welcomeText).size() > 0, "Welcome Text is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(getStartedButton).size() > 0, "Get Started Button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(loginIn).size() > 0, "Login in link not loaded");
        }
    }

    public void clickGetStarted() {
        waitUntilElementIsPresent(getStartedButton);
        clickWhenClickable(getStartedButton);
    }
}
