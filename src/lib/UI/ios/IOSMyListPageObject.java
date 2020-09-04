package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {

    static {
//        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
    }

    public IOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
