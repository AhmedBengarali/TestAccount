package acceptancetests.steps;

import acceptancetests.base.DriverUtil;
import acceptancetests.base.EncryptionHelper;
import acceptancetests.pages.BasePage;
import acceptancetests.pages.IWAPage;
import acceptancetests.pages.MyPGAOverviewPage;

import cucumber.api.java8.En;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class WebSteps implements En {

    private static final Logger LOG = LoggerFactory.getLogger(WebSteps.class);
    BasePage base;
    IWAPage iwaPage;
    MyPGAOverviewPage myPGAOverviewPage;
    String encryptionPassword = "PGAEncryptionPwd";
  	String encryptedPassword = "C3phLO96nQQHeTkaIRIoRA==:AqqC17BEdaGNU6EKgeE+tg==";

    public WebSteps() {
        Given("^I navigate to the PGA webapp$", () -> {
            WebDriver driver = DriverUtil.getDriver();
            driver.navigate().to(DriverUtil.getTargetWebUrl());
            iwaPage = new IWAPage(driver);
        });
        
        Given("^the siteminder login site is displayed$", () -> {
            Assert.assertTrue("Login".equals(iwaPage.getTitle()));
        });
        When("^I enter username and password$", () -> {
            iwaPage.enterName("QDPGA00");
            String decryptedPassword = EncryptionHelper.encrypt(encryptionPassword, "qdpgatest");
            LOG.info("Encrypted password: " + decryptedPassword);
            iwaPage.enterPassword("qdpgatest");

        });
        When("^I click on login button$", () -> {
            myPGAOverviewPage = iwaPage.clickLoginButton();
            Assert.assertTrue(myPGAOverviewPage.checkLogoutLink());
        });

        Then("^MyPGA homepage is displayed$", () -> {
            myPGAOverviewPage.waitForSpinnerToComplete();
            Assert.assertTrue("My PGAs".equals(myPGAOverviewPage.getTitle()));
        });
        Given("^I navigate to MyPGA homepage$", () -> {
            WebDriver driver = DriverUtil.getDriver();
            driver.navigate().to(DriverUtil.getTargetWebUrl());
            myPGAOverviewPage = new MyPGAOverviewPage(driver);
        });
  

        Given("I am logged in as  \"([^\"]*)\"$", (String testUserLogin) -> {
            for (int i = 0; i < 5; i++) {
                DriverUtil.getDriver().get("http://" + testUserLogin + ":q270932@192.168.0.113:8080/");
                LOG.info(iwaPage.getTitle());
                try {

                        Assert.assertTrue("My PGAs".equals(iwaPage.getTitle()));
                        break;
                    } catch (Exception e) {
                        if (i != 4) {
                        continue;
                    } else {
                        throw new RuntimeException("Log in was not successful");
                    }
                }
            }

        });
        When("^I click on logout icon$", () -> {
            myPGAOverviewPage.clickLogoutButton();

        });
        When("^I navigate to PGA webapp URL$", () -> {
            WebDriver driver = DriverUtil.getDriver();
            driver.navigate().to(DriverUtil.getTargetWebUrl());
            iwaPage = new IWAPage(driver);
        });
        When("^I see logout confirmation page$", () -> {
            Assert.assertFalse(myPGAOverviewPage.checkLogoutLink());
        });
        Then("^I see siteminder login page$", () -> {
            Assert.assertTrue("Login".equals(iwaPage.getTitle()));
        });
    }
}
