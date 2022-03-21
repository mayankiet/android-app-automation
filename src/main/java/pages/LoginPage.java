package pages;

import constant.Constants;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import testBase.BaseTest;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BaseTest {

    public void loginToApp() {
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        AndroidElement androidElement = (AndroidElement) appiumDriver.findElement(By.xpath(Constants.MOBILE_NUMBER_FIELD));
        androidElement.sendKeys(Constants.MOBILE_NUMBER);
    }
}
