package tests;

import java.time.Duration;
import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangesScreenOrientationOnSearchResult() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String titelBeforRotation = articlePageObject.getArticleTitle("Java (programming language)");
        this.rotateScreenLandscape();
        String titelAfterRotation = articlePageObject.getArticleTitle("Java (programming language)");
        assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterRotation
        );

        this.rotateScreenPortrait();
        String titelAfterSecondRotation = articlePageObject.getArticleTitle("Java (programming language)");
        assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterSecondRotation
        );
    }

    @Test
    public void testChechSearchArticleInBackground() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(Duration.ofSeconds(2));
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
