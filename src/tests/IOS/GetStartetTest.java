package tests.IOS;

import lib.IOSTestCase;
import lib.UI.WelcomePageObgect;
import org.junit.Test;

public class GetStartetTest extends IOSTestCase {

    @Test
    public void testPassThroughWelcome() {
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
