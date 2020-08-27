package lib;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected Platform platform;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.platform = new Platform();
        driver = this.platform.getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration seconds) {
        driver.runAppInBackground(seconds);
    }
}
