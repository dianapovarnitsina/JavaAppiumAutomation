package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUN,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_INPUT_LINE,
        SEARCH_RESULT_LIST;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATEDS METHODS
    private static String getResultSearchElement(String subString) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    public void waitForSearchResult(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        System.out.println(searchResultXpath);
        this.waitForElementPresent(searchResultXpath, "Cannot find search  result with subString" + subString);
    }

    public void intitSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void checkForMissingSearchElements() {
        this.waitForElementNotPresent(SEARCH_RESULT_LIST, "The element is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUN, searchLine, "Cannot find and type into search input", 5);
    }

    public void clickByArticleWhithSubsting(String subString) {
        String searchResultXpath = getResultSearchElement(subString);
        this.waitForElementAndClick(searchResultXpath, "Cannot find and click search result with subString" + subString, 10);
    }

    public void clearTheInputLine() {
        this.waitForElementAndClear(SEARCH_INPUT_LINE, "Cannot find search field", 5);
    }

    public int getAmountOfFoundArticle() {
        this.waitForElementPresent(
            SEARCH_RESULT_ELEMENT,
            "Cannot find anything by the request ",
            15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot faind empry result element", 15);
    }

    public void assertThereIsNotResultOfSearch() {
        this.asertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }
}
