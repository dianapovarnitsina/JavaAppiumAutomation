package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

public class MainPageObject {

    public AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(String locator, String errorMassage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMassage + "\n");
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.getLocatorByString(locator)));

        if (titleElement == null) {
            throw new AssertionError(errorMassage);
        }
    }

    public WebElement waitForElementPresent(String locator, String errorMassage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMassage) {
        return waitForElementPresent(locator, errorMassage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMassage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMassage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMassage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(this.getLocatorByString(locator))
        );
    }

    public WebElement waitForElementAndClear(String locator, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMassage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String value, String errorMassage) {
        WebElement titleElement = waitForElementPresent(
            locator,
            errorMassage,
            15
        );

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals(
            "We see unexpected title",
            value,
            articleTitle
        );
    }

    public void checkingTheSearchResultForWordContent(String locator, String containedstrings, String errorMassage, long timeOutInSeconds) {
        WebElement titleElement = waitForElementPresent(
            locator,
            "Cannot find article title",
            15
        );

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertTrue(errorMassage,
            articleTitle.contains(containedstrings));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).moveTo(PointOption.point(x, endY)).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(2000);
    }

    public void swipeUpToFindElement(String locator, String errorMassage, int maxSwipes ) {
        int alreadySwiped = 0;

        while (driver.findElements(this.getLocatorByString(locator)).size()==0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find lement by swiping up. \n " + errorMassage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeUPTitleElementAppear(String locator, String errorMassage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMassage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMassage, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMassage, timeOutInSecond);
        return element.getAttribute(attribute);
    }

    public void asertElementNotPresent (String locator, String errorMassage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defauldMassage = "An element " + locator + " suppourse to be present";
            throw new AssertionError(defauldMassage + " " + errorMassage);
        }
    }

    public int getAmountOfElements(String locator) {
        List elements = driver.findElements(this.getLocatorByString(locator));
        return elements.size();
    }

    public void clickElementToTheRightUppeeCorner(String locator, String errorMassage) {
        WebElement element = this.waitForElementPresent(locator + "/..", errorMassage);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();

        int pointToClickX = (rightX + width) -3;
        int pointToClickY = middleY;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }

    public void swipeElementToLeft(String locator, String errorMassage) {
        WebElement element = waitForElementPresent(
            locator,
            errorMassage,
            10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(rightX, middleY));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

        if (Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(leftX, middleY));
        } else {
            int ofsetX = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(ofsetX, 0));
        }
        action.release();
        action.perform();
    }

    private By getLocatorByString(String locatorWithType) {
        String [] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byTipe = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byTipe.equals("xpath")) {
            System.out.println(By.xpath(locator));
            return By.xpath(locator);
        } else if (byTipe.equals("id")) {
            System.out.println(By.id(locator));
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator:" + locatorWithType);
        }
    }
}
