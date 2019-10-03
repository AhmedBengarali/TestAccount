package acceptancetests.steps;

import acceptancetests.base.DriverUtil;
import acceptancetests.pages.MyPGAOverviewPage;
import acceptancetests.pages.SearchMyPGATable;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;

public class pgaSearchSteps implements En {
    MyPGAOverviewPage myPGAOverviewPage;
    SearchMyPGATable searchMyPGATable;
    String encryptionPassword = "PGAEncryptionPwd";
    String encryptedPassword = "C3phLO96nQQHeTkaIRIoRA==:AqqC17BEdaGNU6EKgeE+tg==";

    public pgaSearchSteps() {
        When("^I click into search field$", () -> {
            WebDriver driver = DriverUtil.getDriver();
            myPGAOverviewPage = new MyPGAOverviewPage(driver);
            searchMyPGATable = myPGAOverviewPage.ClickOnSearchField();

        });

        And("^I enter \"([^\"]*)\"$", (String arg0) -> {
            searchMyPGATable.searchkey(arg0);


        });

        And("^I enter return$", () -> {
            searchMyPGATable.submitsearch();
        });

        Then("^the search result shows one PGA-object$", () -> {
            searchMyPGATable.checkresult();

        });

    }
}
