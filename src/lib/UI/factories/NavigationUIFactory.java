package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.NavigationUI;
import lib.UI.android.AndroidNavigationUI;
import lib.UI.ios.IOSNavigationUI;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else {
            return new IOSNavigationUI(driver);
        }
    }
}
