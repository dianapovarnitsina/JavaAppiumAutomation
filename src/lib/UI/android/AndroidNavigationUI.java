package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {

    static {
        My_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
