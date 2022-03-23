package lummoShop;

import annotations.Author;
import annotations.TestCaseNotes;
import annotations.Tester;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LummoShopPage;
import testBase.BaseTest;
import java.net.MalformedURLException;

public class LummoShopTest extends BaseTest {
    LummoShopPage lummoShopPage;

    public LummoShopTest(){
        super();
    }

    @BeforeMethod
    public void setupLummoAppDesiredCapabilities() throws MalformedURLException, InterruptedException {
        lummoShopPage = new LummoShopPage();
        setUpAndLaunchApplication();
    }

    @Author(name = Tester.MAYANK)
    @TestCaseNotes(steps = "1. Launch the application" +
            "2. Login to the app" +
            "3. Click on parasaram tab" +
            "4. Click on buat promo" +
            "5. Check for promo exist or not" +
            "6. If not create new promo - Click on Cek Kembali(1) button, Click on Lanjut button, Click on Lanjut button, Click\n" +
            "on Terbitkan" +
            "7. Check for promo created")
    @Test
    public void verifyUserShouldBeAbleToCreatePromo(){
        lummoShopPage.loginToApp();
        Assert.assertTrue(lummoShopPage.verifyParmasaranTabDisplaying());
        lummoShopPage.clickOnParasaranTab();
        Assert.assertTrue(lummoShopPage.verifyBuatPromoDisplaying());
        lummoShopPage.clickBuatPromo();
        Assert.assertTrue(lummoShopPage.verifyPromoExistOrNot());
        lummoShopPage.clickOnCreateBuatPromoButton();
    }
}
