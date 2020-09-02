package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObgect;
import org.junit.Test;

public class GetStartetTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {
        if (Platform.getInstance().isAndroid()) {
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
