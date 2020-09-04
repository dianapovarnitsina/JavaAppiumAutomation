package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.MainPageObject;
import lib.UI.MyListPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.MyListPageObjectFactory;
import lib.UI.factories.NavigationUIFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class DZ4 extends CoreTestCase {

    private static final  String nameOfFolder = "Learning prodramming";


    @Test
    public void testEx3() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

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
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        articlePageObject.waitForTitleElement("Java (programming language)");
        String articleTitleJava = articlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.iosAuthClose();
        }
        articlePageObject.closeArticle();

        searchPageObject.intitSearchInput();
        if (Platform.getInstance().isIOS()) {
            articlePageObject.iosClearSearchString();
        }

        searchPageObject.typeSearchLine("JBL");
        searchPageObject.clickByArticleWhithSubsting("American audio hardware company");

        articlePageObject.waitForTitleElement("JBL");

        String articleTitleJBL = articlePageObject.getArticleTitle("JBL");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyExistingList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(nameOfFolder);
        }

        myListPageObject.swipeByArticleToDelete(articleTitleJava);
        myListPageObject.waitForArticleToAppearByTitle(articleTitleJBL);
    }

    @Test
    public void testEx6() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
//        String nameOfFolder = "Learning programming";

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.assertElementPresent(
            "id:org.wikipedia:id/view_page_title_text",
            "Not find title",
            0
        );
    }
}
