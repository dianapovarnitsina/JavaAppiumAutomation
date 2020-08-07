package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCanselSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String searchLine = "Linkin Park Dis";
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        int amauntOfSearchResult = searchPageObject.getAmountOfFoundArticle();

        assertTrue(
            "We found too few result",
            amauntOfSearchResult > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        String searchLine = "bhdbnjdnjdsmddx";
        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine(searchLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNotResultOfSearch();
    }
}
