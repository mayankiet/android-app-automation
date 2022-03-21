package promotion;

import annotations.Author;
import annotations.TestCaseNotes;
import annotations.Tester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import testBase.BaseTest;
import java.net.MalformedURLException;

public class PromotionTest extends BaseTest {
    LoginPage loginPage;

    public PromotionTest(){
        super();
    }

    @BeforeMethod
    public void setupMarketingDesiredCapabilities() throws MalformedURLException, InterruptedException {
        setUpAndLaunchApplication();
        loginPage = new LoginPage();
    }

    @Author(name = Tester.MAYANK)
    @TestCaseNotes(steps = "1. Launch the marketing application" +
            "2. Enter mail id and click on next" +
            "3. Enter password and click on submit" +
            "4. Click on grant access and verify")
    @Test(groups="login")
    public void verifyUserShouldBeAbleToLogin() throws InterruptedException, MalformedURLException {
        loginPage.loginToApp();
    }
}
