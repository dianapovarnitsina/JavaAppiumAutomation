package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
        );
    }

    @Test
    public void testSwipeArtical() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWhithSubsting("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFolder();
    }
}
