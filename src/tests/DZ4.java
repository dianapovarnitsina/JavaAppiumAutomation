package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;
import lib.UI.MyListPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class DZ4 extends CoreTestCase {

    @Test
    public void testEx3() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        String searchLine = "Word";
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amauntOfSearchResult = searchPageObject.getAmountOfFoundArticle();
        assertTrue(
            "We found too few result",
            amauntOfSearchResult > 0
        );
        searchPageObject.clearTheInputLine();
        searchPageObject.checkForMissingSearchElements();
    }

    @Test
    public void testEx5() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String nameOfFolder = "Learning programming";

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitleJava = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("JBL");
        searchPageObject.clickByArticleWhithSubsting("American audio hardware company");
        articlePageObject.waitForTitleElement();
        String articleTitleJBL = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyExistingList(nameOfFolder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.openFolderByName(nameOfFolder);
        myListPageObject.swipeByArticleToDelete(articleTitleJava);
        myListPageObject.waitForArticleToAppearByTitle(articleTitleJBL);
    }

    @Test
    public void testEx6() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String nameOfFolder = "Learning programming";

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.assertElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Not find title",
            0
        );
    }
}
