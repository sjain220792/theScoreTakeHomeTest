package core.Driver;

import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class MobileApp extends Driver {

    private static final Logger logger = LoggerFactory.getLogger(MobileApp.class);

    public enum Direction {
        UP,
        DOWN
    }

    public static String captureScreenshot(String testName) throws IOException {
        String fileName = getSanitisedName(testName) + ".png";
        String imageFolder = imagesFolderName;

        String path = imagesFolderPath + "/" + fileName;
        File f = appiumDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File(path));
        return imageFolder + "/" + fileName;
    }

    public static void closeApp() {
        try {
            terminateApp();
            logger.info("Goodnight Apps");
        } catch (Exception e) {
            logger.warn("Unable to close App");
            logger.warn(e.toString());
        }
    }

    public static void terminateApp() {
        String appId = null;
        if (appiumDriver != null) {
            try {
                if (appiumDriver instanceof AndroidDriver) {
                    appId = (String) appiumDriver.getCapabilities().getCapability("appPackage");
                } else {
                    Assert.fail("Unknown driver type");
                }
                if (appId != null)
                    ((InteractsWithApps) appiumDriver).activateApp(appId);
            } catch (Exception e) {
                logger.warn(e.toString());
            }
        }
    }

    public static void openApp() {
        try {
            activateApp();
        } catch (Exception e) {
            logger.warn("Unable to open App");
            logger.warn(e.toString());
        }
    }

    public static void activateApp() {
        String appId = null;
        if (appiumDriver != null) {
            try {
                if (appiumDriver instanceof AndroidDriver) {
                    appId = (String) appiumDriver.getCapabilities().getCapability("appPackage");
                } else {
                    Assert.fail("Unknown driver type");
                }
                if (appId != null)
                    ((InteractsWithApps) appiumDriver).activateApp(appId);
            } catch (Exception e) {
                logger.error("Error activating app: " + appId, e);
            }
        }
    }

    public static void clearCookies() {
        appiumDriver.manage().deleteAllCookies();
    }

    public static void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'");
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;
        int edgerBorder = 10;

        Dimension dims = appiumDriver.manage().window().getSize();
        int startX = dims.width / 2;
        int startY = dims.height / 4;
        int endX, endY;

        switch (dir) {
            case UP:
                endX = dims.width / 2;
                endY = edgerBorder;
                break;
            case DOWN:
                endX = dims.width / 2;
                endY = dims.height - edgerBorder;
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' not supported");
        }
        try {
            fingerSwipe(startX, startY, endX, endY, PRESS_TIME);
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            logger.error("Error in SwipeScreen: " + e);
        }
    }

    public static void fingerSwipe(int startX, int startY, int endX, int endY, long time) {
        PointerInput touchAction = new PointerInput(PointerInput.Kind.TOUCH, "touchAction");
        Interaction movetoStart = touchAction.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY);
        Interaction pressDown = touchAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction movetoEnd = touchAction.createPointerMove(Duration.ofMillis(time), PointerInput.Origin.viewport(), endX, endY);
        Interaction pressUp = touchAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(touchAction, 0);
        swipe.addAction(movetoStart);
        swipe.addAction(pressDown);
        swipe.addAction(movetoEnd);
        swipe.addAction(pressUp);

        appiumDriver.perform(Arrays.asList(swipe));
    }

    public static String getSanitisedName(String input) {
        return input.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }
}
