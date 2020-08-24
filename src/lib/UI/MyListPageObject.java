package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_MAME}']",
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_MAME}", nameOfFolder);
    }

    private static String getFolderXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.waitForElementAndClick(
            getFolderXpathByName(nameOfFolder),
            "Cannot find folder By name" + nameOfFolder,
            5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getFolderXpathByTitle(articleTitle), "Cannot find saved article by title" + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getFolderXpathByTitle(articleTitle), "Saved article srill present with title" + articleTitle, 15
        );
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(
            getFolderXpathByName(articleTitle),
            "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(articleTitle);
    }
}
