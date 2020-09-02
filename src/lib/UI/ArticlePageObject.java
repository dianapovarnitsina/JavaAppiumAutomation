package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITILE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_ME_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        FOLDER;

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
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
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
