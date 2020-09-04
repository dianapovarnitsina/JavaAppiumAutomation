package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUN = "xpath://XCUIElementTypeSearchField[@type='XCUIElementTypeSearchField']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";

//        SEARCH_INPUT_LINE = "id:org.wikipedia:id/search_src_text";
//        SEARCH_RESULT_LIST = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@index='1']";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
