package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITILE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_ME_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        FOLDER = "//*[@class='android.widget.TextView'][@text='{FOLDER_MAME}']";

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER.replace("{FOLDER_MAME}", nameOfFolder);
    }

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //ожидаем появления заголовка на прогружаемой странице
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITILE, "Cannot find article title on page", 15);
    }

    //получаем текст заголовка
    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    //свайпаем вверх, спускаемся к низу страницы
    public void swipeToFolder() {
        this.swipeUpToFindElement(
            FOOTER_ELEMENT,
            "Cannot find the end of article",
            20
        );
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            OPTIONS_ADD_TO_ME_LIST_BUTTON,
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            ADD_TO_MY_LIST_OVERLAY,
            "Cannot find 'Got it' ip overlay",
            5
        );

        this.waitForElementAndClear(
            MY_LIST_NAME_INPUT,
            "Cannot find input to set name of articles folder",
            5
        );


        this.waitForElementAndSendKeys(
            MY_LIST_NAME_INPUT,
            nameOfFolder,
            "Cannot put text into articles folder input",
            5
        );

        this.waitForElementAndClick(
            MY_LIST_OK_BUTTON,
            "Cannot press OK button",
            5
        );
    }

    public void addArticleToMyExistingList(String nameOfFolder) {
        this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            OPTIONS_ADD_TO_ME_LIST_BUTTON,
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            getFolderXpathByName(FOLDER),
            "",
            5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
            CLOSE_ARTICLE_BUTTON,
            "Cannot close article, cannot find X link",
            5
        );
    }
}
