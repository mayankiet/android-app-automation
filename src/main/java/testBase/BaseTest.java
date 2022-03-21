package testBase;

import constant.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static AndroidDriver<MobileElement> appiumDriver;

    public static void setUpAndLaunchApplication() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Constants.PLATFORM_NAME);
        desiredCapabilities.setCapability(Constants.PLATFORM_VERSION, Constants.PLATFORMVERSION_NAME);
        desiredCapabilities.setCapability(Constants.DEVICE_NAME, Constants.ANDROID_DEVICENAME);
        desiredCapabilities.setCapability(Constants.UUID, Constants.EMULATOR_UUID);
        desiredCapabilities.setCapability(Constants.AUTOMATION_NAME, Constants.AUTOMATIONNAME);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty(Constants.USR_DIR) + Constants.LUMMO_APP);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Constants.APP_PACKAGE);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Constants.APP_ACTIVITY);
        URL url;
        url = new URL(Constants.HOST_URL);
        appiumDriver = new AndroidDriver<MobileElement>(url, desiredCapabilities);
        appiumDriver.context(getWebDriverContext(appiumDriver));
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static String getWebDriverContext(AppiumDriver driver){
        ArrayList<String> context = new ArrayList<>(appiumDriver.getContextHandles());
        for (String contextName : context) {
            if(!context.equals("NATIVE_APP")){
                return contextName;
            }
        }
        return null;
    }
}
