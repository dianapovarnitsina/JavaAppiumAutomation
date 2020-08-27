package tests;

import lib.CoreTestCase;
import lib.UI.WelcomePageObgect;
import org.junit.Test;

public class GetStartetTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {
        if (this.platform.isAndroid()) {
            return;
        }
        WelcomePageObgect welcomePage = new WelcomePageObgect(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLerrnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
