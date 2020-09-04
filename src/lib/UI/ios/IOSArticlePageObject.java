package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
//        TITILE = "id:Java (programming language)";
        TITILE = "id:{TITLE}";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_ME_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
//        FOLDER = "//*[@class='android.widget.TextView'][@text='{FOLDER_MAME}']";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
