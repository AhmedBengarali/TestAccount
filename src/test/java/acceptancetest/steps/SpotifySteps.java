package acceptancetest.steps;

import acceptancetest.base.DriverUtil;
import acceptancetest.page.SpotifyHomePage;
import acceptancetest.page.SpotifyLoginPage;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class SpotifySteps implements En {

    private static final Logger LOG = LoggerFactory.getLogger(SpotifySteps.class);
    SpotifyHomePage spotifyHomePage;
    SpotifyLoginPage spotifyLoginPage;
    public SpotifySteps() {
        Given("^Go to Spotify Login Page$", () -> {
            WebDriver driver = DriverUtil.getDriver();
            driver.navigate().to(DriverUtil.getSpotifyUrl());
            spotifyHomePage = new SpotifyHomePage(driver);
            spotifyLoginPage = spotifyHomePage.OpeningLoginPage();
        });
        When("^Enter \"([^\"]*)\"$", (String arg0) -> {
            spotifyLoginPage.inputUsername(arg0);
        });
        And("^Enter \"([^\"]*)\" and Submit$", (String arg0) -> {
            spotifyLoginPage.inputPassword(arg0);
            spotifyLoginPage.ClickConnectButton();
        });
        Then("^Go to Account setting$", () -> {
            LOG.info(spotifyLoginPage.getTitle());

        });
        And("^Check the Type of The Account$", () -> {
        });

    }
}
