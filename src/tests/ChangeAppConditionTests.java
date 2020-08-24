package tests;

import java.time.Duration;
import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangesScreenOrientationOnSearchResult() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String titelBeforRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titelAfterRotation = articlePageObject.getArticleTitle();
        assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterRotation
        );

        this.rotateScreenPortrait();
        String titelAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals(
            "Article title have been changed after screen  rotation",
            titelBeforRotation,
            titelAfterSecondRotation
        );
    }

    @Test
    public void testChechSearchArticleInBackground() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(Duration.ofSeconds(2));
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
