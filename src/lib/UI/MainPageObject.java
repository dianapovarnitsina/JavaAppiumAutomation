package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {

    public AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(By by, String errorMassage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMassage + "\n");
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        if (titleElement == null) {
            throw new AssertionError(errorMassage);
        }
    }

    public WebElement waitForElementPresent(By by, String errorMassage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String errorMassage) {
        return waitForElementPresent(by, errorMassage, 5);
    }

    public WebElement waitForElementAndClick(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMassage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String value, String errorMassage) {
        WebElement titleElement = waitForElementPresent(
            by,
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

    public void checkingTheSearchResultForWordContent(By by, String containedstrings, String errorMassage, long timeOutInSeconds) {
        WebElement titleElement = waitForElementPresent(
            by,
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

        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(2000);
    }

    public void swipeUpToFindElement(By by, String errorMassage, int maxSwipes ) {
        int alreadySwiped = 0;

        while (driver.findElements(by).size()==0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find lement by swiping up. \n " + errorMassage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMassage, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMassage, timeOutInSecond);
        return element.getAttribute(attribute);
    }

    public void asertElementNotPresent (By by, String errorMassage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defauldMassage = "An element " + by.toString() + " suppourse to be present";
            throw new AssertionError(defauldMassage + " " + errorMassage);
        }
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeElementToLeft(By by, String errorMassage) {
        WebElement element = waitForElementPresent(
            by,
            errorMassage,
            10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
            .press(rightX, middleY)
            .waitAction(300)
            .moveTo(leftX, middleY)
            .release()
            .perform();
    }
}
