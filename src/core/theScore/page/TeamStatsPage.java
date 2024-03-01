package core.theScore.page;

import core.theScore.common.AbstractAppPage;
import org.testng.Assert;

public class TeamStatsPage extends AbstractAppPage {

    public String PAGE_NAME = "TEAM STATS";
    public static String topNavigationPane = "//*[@resource-id='com.fivemobile.thescore:id/tabLayout']";
    public static String backButton = "//*[@content-desc='Navigate up']";
    public static String teamLogo = "//*[@resource-id='com.fivemobile.thescore:id/team_logo']";
    public static String favIcon = "//*[@resource-id='com.fivemobile.thescore:id/action_alert']";
    public static String statsLabel = "//*[@resource-id='com.fivemobile.thescore:id/recyclerView']";
    public static String rankLabel = "//*[@resource-id='com.fivemobile.thescore:id/header_secondary_text']";

    public void verifyPageLoaded() {
        super.verifyPageLoaded();
        if (isMobileElementPresent(topNavigationPane)) {
            Assert.assertTrue(waitUntilElementIsPresent(topNavigationPane).size() > 0, "Top navigation pane is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(backButton).size() > 0, "Back button is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(teamLogo).size() > 0, "Team logo is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(favIcon).size() > 0, "Favourite icon is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(statsLabel).size() > 0, "Teams Stats is not loaded");
            Assert.assertTrue(waitUntilElementIsPresent(rankLabel).size() > 0, "Team Rank for stats is not loaded");
        }
    }
}
