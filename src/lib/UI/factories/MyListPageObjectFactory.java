package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.MyListPageObject;
import lib.UI.android.AndroidMyListPageObject;
import lib.UI.ios.IOSMyListPageObject;

public class MyListPageObjectFactory {

    public static MyListPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        } else {
            return new IOSMyListPageObject(driver);
        }
    }
}
