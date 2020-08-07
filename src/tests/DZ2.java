package tests;

import lib.CoreTestCase;
import lib.UI.MainPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class DZ2 extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testEx2() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.assertElementHasText(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            "Search Wikipedia",
            "Cannot find Search Wikipedia input"
        );
    }

    @Test
    public void testEx4() {
        mainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' input",
            5
        );

        mainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search…')]"),
            "Diana",
            "Cannot find search input",
            5
        );

        mainPageObject.checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='8']//*[@instance='1']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
        );

        mainPageObject.checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='10']//*[@instance='3']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
        );

        mainPageObject.checkingTheSearchResultForWordContent(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@instance='12']//*[@instance='5']"),
            "Diana",
            "The string does not contain 'Diana'",
            15
        );
    }
}
