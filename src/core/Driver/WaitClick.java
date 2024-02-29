package core.Driver;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class WaitClick extends Driver {
    private static Logger logger = LoggerFactory.getLogger(WaitClick.class);
    protected final static String loadingWheel = "loading";

    public static WebElement getMobileElementFromLocator(String locator) {
        try {
            if ((locator.contains("//")) || (locator.contains("(//"))) {
                return appiumDriver.findElement(AppiumBy.xpath(locator));
            } else if (MobileApp.browser.contains("Android")) {
                return appiumDriver.findElement(new AppiumBy.ByAccessibilityId(locator));
            } else if (MobileApp.browser.contains("iOS")) {
                return appiumDriver.findElement(AppiumBy.name(locator));
            } else {
                logger.error("Device not recognised");
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public static List<WebElement> getMobileElementsFromLocator(String locator) {
        try {
            if ((locator.contains("//")) || (locator.contains("(//"))) {
                return appiumDriver.findElements(AppiumBy.xpath(locator));
            } else if (MobileApp.browser.contains("Android")) {
                return appiumDriver.findElements(new AppiumBy.ByAccessibilityId(locator));
            } else if (MobileApp.browser.contains("iOS")) {
                return appiumDriver.findElements(AppiumBy.name(locator));
            } else {
                logger.error("Device not recognised");
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public static List<WebElement> waitUntilElementIsPresent(String locator) {
        try {
            int i = 0;
            while (!isMobileElementPresentGeneric(locator) && i < 10) {
                i++;
                pause(500);
            }
            List<WebElement> mobileElementList = getMobileElementsFromLocator(locator);
            logger.info("# of Elements found with locator: " + locator + " is: " + mobileElementList.size());
            return mobileElementList;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

    public static boolean isMobileElementPresent(String locator) {
        List<WebElement> mobileElementList = waitUntilElementIsPresent(locator);
        return mobileElementList.size() > 0;
    }

    public static boolean isMobileElementPresentGeneric(String locator) {
        List<WebElement> mobileElementList = getMobileElementsFromLocator(locator);
        return mobileElementList.size() > 0;
    }

    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            logger.warn("Pause was interrupted");
        }
    }

    public static void clickWhenClickable(String locator) {
        waitUntilElementIsPresent(locator);
        try {
            if (locator.contains("//") || locator.startsWith("(")) {
                WebElement element = appiumDriver.findElement(AppiumBy.xpath(locator));
                element.click();
            } else if (browser.contains("iOS")) {
                WebElement element = appiumDriver.findElement(AppiumBy.name(locator));
                element.click();
            } else {
                WebElement element = appiumDriver.findElement(new AppiumBy.ByAccessibilityId(locator));
                element.click();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public static void scrollDownToElement(String locator) {
        int i = 0;
        if (browser.contains("Android")) {
            while (!isMobileElementPresent(locator) && i < 10) {
                MobileApp.swipeScreen(MobileApp.Direction.UP);
                waitForLoadingToFinish();
                i++;
                logger.info(i + ". Scrolling to element: " + locator);
            }
        } else {
            int device_y = appiumDriver.manage().window().getSize().getHeight() - 100;
            int element_y = getMobileElementFromLocator(locator).getLocation().getY()
                    + getMobileElementFromLocator(locator).getSize().height;

            while (!isMobileElementPresent(locator) || (device_y < element_y && i < 5)) {
                logger.info(i + ". Scrolling: device_y/element_y - " + device_y + "/" + element_y);
                MobileApp.swipeScreen(MobileApp.Direction.UP);
                waitForLoadingToFinish();
                i++;
                element_y = getMobileElementFromLocator(locator).getLocation().getY();
            }
        }
    }

    public static void waitForLoadingToFinish() {
        int i = 0;
        while (isMobileElementPresentGeneric(loadingWheel) && i < 5) {
            pause(500);
            logger.info("Page is loading..." + i);
            i++;
        }
    }

    public static void writeToMobileField(String text, String locator) {
        try {
            waitUntilElementIsPresent(locator);
            WebElement element = getMobileElementFromLocator(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}