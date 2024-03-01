package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class TeamPlayerStatsPage extends AbstractAppPage {

    public String PAGE_NAME = "PLAYER STATS";
    public static String topNavigationPane = "//*[@resource-id='com.fivemobile.thescore:id/tabLayout']";
    public static String backButton = "//*[@content-desc='Navigate up']";
    public static String teamLogo = "//*[@resource-id='com.fivemobile.thescore:id/team_logo']";
    public static String favIcon = "//*[@resource-id='com.fivemobile.thescore:id/action_alert']";
    public static String playersTable = "//*[@resource-id='com.fivemobile.thescore:id/recyclerView']";
    public static String playersStats = "//*[@resource-id='com.fivemobile.thescore:id/layout_horizontal_list']";

    public void verifyPageLoaded(String locator) {
        super.verifyPageLoaded();
        if (isMobileElementPresent(topNavigationPane)) {
            Assert.assertTrue(waitUntilElementIsPresent(topNavigationPane).size() > 0, "Top navigation pane is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(backButton).size() > 0, "Back button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(teamLogo).size() > 0, "Team logo is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(favIcon).size() > 0, "Favourite icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(playersTable).size() > 0, "Players table is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(playersStats).size() > 0, "Player stats is not loaded");
            Assert.assertTrue(isMobileElementPresent(getGenericText(locator)), "Correct team is not loaded");
        }
    }
}
