package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle("Java (programming language)");

        assertEquals(
            "We see unexpected title",
            "Java (programming language)",
            articleTitle
        );
    }

    @Test
    public void testSwipeArtical() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        articlePageObject.swipeToFolder();
    }
}
