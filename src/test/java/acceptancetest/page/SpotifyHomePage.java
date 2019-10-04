package acceptancetest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpotifyHomePage extends BasePage {

    public static final String URL="#";
    private static final Logger LOG = LoggerFactory.getLogger(SpotifyHomePage.class);

    @FindBy(how = How.XPATH,using = "//a[contains(text(),'Se Connecter')]")
    private WebElement btnConnect;



    public SpotifyHomePage(WebDriver driver) {
        super(driver);
        pause(5000);
    }

    public SpotifyLoginPage OpeningLoginPage(){
        LOG.debug("SpotifyHomePage : Opening Spotify Login Page");
        btnConnect.click();
        return new SpotifyLoginPage(driver);
    }

    public void close() { this.driver.close(); }
}
