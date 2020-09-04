package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.NavigationUI;

public class IOSNavigationUI extends NavigationUI {

    static {
        My_LISTS_LINK = "id:Saved";
    }

    public IOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
