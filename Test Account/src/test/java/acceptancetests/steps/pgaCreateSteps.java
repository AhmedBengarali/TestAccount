/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceptancetests.steps;

import acceptancetests.base.DriverUtil;
import acceptancetests.pages.CreateNewPGAPage;
import acceptancetests.pages.IWAPage;
import acceptancetests.pages.MyPGAOverviewPage;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 *
 * @author jlassih
 */
public class pgaCreateSteps implements En {

    private static final Logger LOG = LoggerFactory.getLogger(pgaCreateSteps.class);
    IWAPage iwaPage;
    MyPGAOverviewPage myPGAOverviewPage;
    CreateNewPGAPage createNewPGAPage;
    String encryptionPassword = "PGAEncryptionPwd";
    String encryptedPassword = "C3phLO96nQQHeTkaIRIoRA==:AqqC17BEdaGNU6EKgeE+tg==";

    public pgaCreateSteps() {

          When("^I click on Create PGA button$", () -> {
     WebDriver driver = DriverUtil.getDriver();
            driver.navigate().to(DriverUtil.getTargetWebUrl());
            myPGAOverviewPage = new MyPGAOverviewPage(driver);
            createNewPGAPage = myPGAOverviewPage.clickCreatePGA();

        });
           When("^I enter PGA-name \"([^\"]*)\"$", (String arg1) -> {
            // Write code here that turns the phrase above into concrete actions

            createNewPGAPage.pgaName(arg1);

        });
        When("^I select product \"([^\"]*)\"$", (String arg1) -> {
            // Write code here that turns the phrase above into concrete actions
            createNewPGAPage.productName(arg1);
        });

        Then("^No PGA-object is created$", () -> {
       LOG.info(createNewPGAPage.getTitle());
            Assert.assertTrue("My PGAs".equals(createNewPGAPage.getTitle()));
        });
        When("^I click on save PGA button$", () -> {
            // Write code here that turns the phrase above into concrete actions
            createNewPGAPage.clickSavePGA();
        });
         /*###########################################
           #      Create 10 PGA Object               #                
           ###########################################*/

        And("^I enter PGA name as \"([^\"]*)\"$", (String arg0) -> {
            WebDriver driver = DriverUtil.getDriver();

            myPGAOverviewPage = new MyPGAOverviewPage(driver);
            createNewPGAPage.pgaName(arg0);

        });
        
        And("^I enter Scope name as \"([^\"]*)\"$", (String arg0) -> {
            createNewPGAPage.Scopename(arg0);
        });

        And("^I select Application as \"([^\"]*)\"$", (String arg0) -> {
            createNewPGAPage.AppSelect(arg0);
        });

        And("^I select Product as \"([^\"]*)\"$", (String arg0) -> {
            createNewPGAPage.productName(arg0);
        });

        And("^I select Subproduct as \"([^\"]*)\"$", (String arg0) -> {
        });



        Then("^new PGA-object is created$", () -> {
        });
        And("^overview page of new created PGA object is displayed$", () -> {
        });




    }
}
