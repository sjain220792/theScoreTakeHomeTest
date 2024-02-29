package core.theScore.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import core.Driver.WaitClick;


public class AbstractAppPage extends WaitClick {
    private Logger logger = LoggerFactory.getLogger(AbstractAppPage.class);
    public static String tabLink = "//*[@text='%s']";
    public static String backButton = "//*[@content-desc='Navigate up']";
    public static String genericTextLabel = "//*[contains(@text,'%s')]";


    public void verifyPageLoaded() {
        waitUntilElementIsPresent(loadingWheel);
    }

    public void clickTab(String locator) {
        String xpath = String.format(tabLink, locator);
        waitUntilElementIsPresent(xpath);
        clickWhenClickable(xpath);
    }

    public void clickBackButton() {
        waitUntilElementIsPresent(backButton);
        clickWhenClickable(backButton);
    }

    public String getGenericText(String locator) {
        return String.format(genericTextLabel, locator);
    }

}
