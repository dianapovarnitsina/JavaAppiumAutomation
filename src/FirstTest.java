import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
}