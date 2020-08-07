package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITILE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_ME_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    //ожидаем появления заголовка на прогружаемой странице
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITILE), "Cannot find article title on page", 15);
    }

    //получаем текст заголовка
    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    //свайпаем вверх, спускаемся к низу страницы
    public void swipeToFolder() {
        this.swipeUpToFindElement(
            By.xpath(FOOTER_ELEMENT),
            "Cannot find the end of article",
            20
        );
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
            By.xpath(OPTIONS_BUTTON),
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            By.xpath(OPTIONS_ADD_TO_ME_LIST_BUTTON),
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            By.id(ADD_TO_MY_LIST_OVERLAY),
            "Cannot find 'Got it' ip overlay",
            5
        );

        this.waitForElementAndClear(
            By.id(MY_LIST_NAME_INPUT),
            "Cannot find input to set name of articles folder",
            5
        );


        this.waitForElementAndSendKeys(
            By.id(MY_LIST_NAME_INPUT),
            nameOfFolder,
            "Cannot put text into articles folder input",
            5
        );

        this.waitForElementAndClick(
            By.xpath(MY_LIST_OK_BUTTON),
            "Cannot press OK button",
            5
        );
    }

    public void addArticleToMyExistingList(String nameOfFolder) {
        this.waitForElementAndClick(
            By.xpath(OPTIONS_BUTTON),
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            By.xpath(OPTIONS_ADD_TO_ME_LIST_BUTTON),
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            By.xpath("//*[@class='android.widget.TextView'][@text='"+ nameOfFolder +"']"),
            "",
            5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
            By.xpath(CLOSE_ARTICLE_BUTTON),
            "Cannot close article, cannot find X link",
            5
        );
    }
}
