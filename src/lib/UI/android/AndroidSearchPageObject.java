package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
            SEARCH_INPUN = "xpath://*[contains(@text, 'Search…')]";
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
            SEARCH_INPUT_LINE = "id:org.wikipedia:id/search_src_text";
            SEARCH_RESULT_LIST = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@index='1']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
