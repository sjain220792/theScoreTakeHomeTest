package core.theScore.test;

import com.aventstack.extentreports.Status;
import core.Driver.theScoreAppTest;
import core.theScore.page.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSearchTheScoreApp extends theScoreAppTest {
    public Logger logger = LoggerFactory.getLogger(TestSearchTheScoreApp.class);

    @Parameters({"searchTeam", "favTeam", "player"})
    @Test
    public void testSearchTheScoreApp(String searchTeam, String favTeam, String player) throws IOException, InterruptedException {
        AppLoadingPage appLoadingPage = new AppLoadingPage();
        appLoadingPage.verifyPageLoaded();
        log(Status.INFO, "01: theScore Application is loaded");

        appLoadingPage.clickGetStarted();
        ChooseFavouriteLeaguePage chooseFavouriteLeaguePage = new ChooseFavouriteLeaguePage();
        chooseFavouriteLeaguePage.verifyPageLoaded();
        log(Status.INFO, "02: Get Started button clicked and Choose favourite leagues page is loaded");

        chooseFavouriteLeaguePage.clickContinueButton();
        if (chooseFavouriteLeaguePage.isLocationPopUpPresent()) {
            log(Status.INFO, "03: Enable location pop up is displayed");
            chooseFavouriteLeaguePage.clickMayLaterForLocation();
        }

        ChooseFavouriteTeamPage chooseFavouriteTeamPage = new ChooseFavouriteTeamPage();
        chooseFavouriteTeamPage.verifyPageLoaded();
        log(Status.INFO, "04: Select favourite team page loaded");

        chooseFavouriteTeamPage.selectFavTeam(favTeam);
        log(Status.INFO, "05: Favourite team selected");

        chooseFavouriteTeamPage.clickContinueButton();
        CustomiseNotificationsPage customiseNotificationsPage = new CustomiseNotificationsPage();
        customiseNotificationsPage.verifyPageLoaded();
        log(Status.INFO, "06: Customise Notifications page loaded");

        customiseNotificationsPage.clickDoneButton();
        HomePage homePage = new HomePage();
        if (homePage.isTheScoreBetPopUpPresent()) {
            log(Status.INFO, "07: theScore Bet app pop up is present");
            homePage.clickXCloseButton();
        }
        homePage.verifyPageLoaded();
        log(Status.INFO, "08: theScore app home page is loaded");

        homePage.clickOnSearchBar();
        SearchPage searchPage = new SearchPage();
        searchPage.enterTextInSearchBar(searchTeam);
        searchPage.clickAllSearchResults();
        log(Status.INFO, "09: Entered data in Search bar");

        searchPage.clickOnSearchResult(searchTeam);
        TeamsPage teamsPage = new TeamsPage();
        teamsPage.verifyPageLoaded(searchTeam);
        log(Status.INFO, "10: Teams page has loaded");

        TeamStatsPage teamStatsPage = new TeamStatsPage();
        teamStatsPage.clickTab(teamStatsPage.PAGE_NAME);
        teamStatsPage.verifyPageLoaded();
        log(Status.INFO, "11: Team stats page has loaded");

        TeamPlayerStatsPage teamPlayerStatsPage = new TeamPlayerStatsPage();
        teamPlayerStatsPage.clickTab(teamPlayerStatsPage.PAGE_NAME);
        teamPlayerStatsPage.verifyPageLoaded(player);
        log(Status.INFO, "12: Teams player stats page has loaded");

        searchPage.clickBackButton();
        searchPage.verifyPageLoaded();
        log(Status.INFO, "13: Back to the search page");

        homePage.clickBackButton();
        homePage.verifyPageLoaded();
        log(Status.INFO, "14: Back to the home page");
    }
}