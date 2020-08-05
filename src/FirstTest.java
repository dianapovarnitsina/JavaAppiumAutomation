import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/diana.povarnitsina/JavaAppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input",
            5
        );

        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find  'Object-oriented programming language' topic searching by 'Java'",
            15
        );
    }

    @Test
    public void testCanselSearch() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input",
            5
        );

        waitForElementAndClear(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search field",
            5
        );

        waitForElementAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "Cannot find X to cansel search",
            5
        );

        waitForElementNotresent(
            By.id("org.wikipedia:id/search_close_btn"),
            "X is still present on the page",
            5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find Search Wikipedia input",
            5
        );

        WebElement titleElement = waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15
        );

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
        );
    }

    @Test
    public void ex2() {
        assertElementHasText(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Search Wikipedia",
            "Cannot find Search Wikipedia input"
        );
    }

    @Test
    public void ex3() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Word",
            "Cannot find search input",
            5
        );

        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='8']//*[@instance='2']"),
            "Cannot find article title",
            15
        );

        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='10']//*[@instance='4']"),
            "Cannot find article title",
            15
        );

        waitForElementAndClear(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search field",
            5
        );

        waitForElementNotresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@index='1']"),
            "articles  is still present on the page",
            5
        );
    }

    @Test
    public void ex4() {
        waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Diana",
            "Cannot find search input",
            5
        );

        checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='8']//*[@instance='1']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
            );

        checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='10']//*[@instance='3']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
        );

        checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='12']//*[@instance='5']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
        );
    }

    @Test
    public void testSwipeArtical() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Appium",
            "Cannot find search input",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
            "Cannot find 'Appium' article in search",
            5
        );

        waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15
        );

        swipeUpToFindElement(
            By.xpath("//*[@text='View page in browser']"),
            "Cannot find the end of the artile",
            20
        );
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15
        );

        waitForElementAndClick(
            By.xpath("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open artilce options",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find option to add artilce to reading list",
            5
        );

        waitForElementAndClick(
            By.id("org.wikipedia:id/onboarding_button"),
            "Cannot find 'Got it' ip overlay",
            5
        );

        waitForElementAndClear(
            By.id("org.wikipedia:id/text_input"),
            "Cannot find input to set name of articles folder",
            5
        );

        String nameOfFolder = "Learning prodramming";

        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/text_input"),
            nameOfFolder,
            "Cannot put text into articles folder input",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@text='OK']"),
            "Cannot press OK button",
            5
        );

        waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link",
            5
        );

        waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
            "Cannot find navigation button to My List",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@text='"+ nameOfFolder +"']"),
            "Cannot find created folder",
            5
        );

        swipeElementToLeft(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot find saved article"
        );

        waitForElementNotresent(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot delete saved article",
            5
        );
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        String searchLine = "Linkin Park Dis";

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            searchLine,
            "Cannot find search input",
            5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
            By.xpath(searchResultLocator),
            "Cannot find anything by the request " + searchLine,
            15
        );

        int amauntOfSearchResult = getAmountOfElements(
            By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
            "We found too few result",
            amauntOfSearchResult > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        String searchLine = "bhdbnjdnjdsmddx";

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            searchLine,
            "Cannot find search input",
            5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";

        waitForElementPresent(
            By.xpath(emptyResultLabel),
            "Cannot find empty result label ..." + emptyResultLabel,
            15
        );

        asertElementNotPresent(
            By.xpath(searchResultLocator),
            "We've found some result by request " + searchLine
        );
    }

    @Test
    public void testChangesScreenOrientationOnSearchResult() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        String searchLine = "Java";

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            searchLine,
            "Cannot find search input",
            5
        );

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find  'Object-oriented programming language' topic searching by " + searchLine,
            15
        );

        String titelBeforRotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titelAfterRotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
        );

        Assert.assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterRotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titelAfterSecondRotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
        );

        Assert.assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterSecondRotation
        );
    }

    @Test
    public void testChechSearchArticleInBackground() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Cannot find Search Wikipedia input",
            5
        );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Java",
            "Cannot find search input",
            5
        );

        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find Search Wikipedia input",
            5
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
            "Cannot find Artical ...",
            5
        );
    }

    private WebElement waitForElementPresent(By by, String errorMassage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMassage) {
        return waitForElementPresent(by, errorMassage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotresent(By by, String errorMassage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMassage + "\n");
        return wait.until(
            ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String value, String errorMassage) {
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

    private void checkingTheSearchResultForWordContent(By by, String containedstrings, String errorMassage, long timeOutInSeconds) {
        WebElement titleElement = waitForElementPresent(
            by,
            "Cannot find article title",
            15
        );

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertTrue(errorMassage,
            articleTitle.contains(containedstrings));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(2000);
    }

    protected void swipeUpToFindElement(By by, String errorMassage, int maxSwipes ) {
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

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMassage, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, errorMassage, timeOutInSecond);
        return element.getAttribute(attribute);
    }

    private void asertElementNotPresent (By by, String errorMassage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defauldMassage = "An element " + by.toString() + " suppourse to be present";
            throw new AssertionError(defauldMassage + " " + errorMassage);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeElementToLeft(By by, String errorMassage) {
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