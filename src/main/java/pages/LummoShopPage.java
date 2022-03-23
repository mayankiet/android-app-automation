package pages;

import constant.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseTest;
import java.util.concurrent.TimeUnit;

public class LummoShopPage extends BaseTest {

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"loginPage_countryCodeDropDown\"]/android.view.ViewGroup\n")
    WebElement countrycodedropdown;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"dropDownItem_3\"]/android.widget.TextView")
    WebElement countrycode;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"loginPage_countryCodeDropDown\"]/android.view.ViewGroup")
    WebElement mobilenumber;

    @FindBy(name="loginPage_smsLoginButton")
    WebElement viasms;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"otpPage_otpViewWrapper\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText")
    WebElement otp_one;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"otpPage_otpViewWrapper\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText")
    WebElement otp_two;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"otpPage_otpViewWrapper\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.EditText")
    WebElement otp_three;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"otpPage_otpViewWrapper\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.EditText")
    WebElement otp_four;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"otpPage_verifyNumber\"]\n")
    WebElement verify_otp;

    @FindBy(xpath="//android.widget.Button[@content-desc=\"bottomTab_Marketing\"]")
    WebElement parmasaran_tab;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"promotions\"]/android.view.ViewGroup")
    WebElement buat_promo;

    @FindBy(xpath="//android.view.ViewGroup[@content-desc=\"promotions_createAPromoButton\"]\n")
    WebElement buat_promobutton;


    public LummoShopPage() {
        PageFactory.initElements(appiumDriver, this);
    }

    public void loginToApp() {
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        countrycodedropdown.click();
        countrycode.click();
        mobilenumber.sendKeys(Constants.MOBILE_NUMBER);
        viasms.click();
        otp_one.sendKeys("1");
        otp_two.sendKeys("2");
        otp_three.sendKeys("3");
        otp_four.sendKeys("4");
        verify_otp.click();
    }

    public boolean verifyParmasaranTabDisplaying(){
        return parmasaran_tab.isDisplayed();
    }

    public void clickOnParasaranTab(){
        parmasaran_tab.click();
    }

    public boolean verifyBuatPromoDisplaying(){
        return buat_promo.isDisplayed();
    }

    public void clickBuatPromo(){
        buat_promo.click();
    }

    public boolean verifyPromoExistOrNot(){
        return buat_promobutton.isEnabled();
    }

    public void clickOnCreateBuatPromoButton(){
        buat_promobutton.click();
    }
}
