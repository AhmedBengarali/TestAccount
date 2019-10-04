package acceptancetest.page;

import acceptancetest.base.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class SpotifyLoginPage extends BasePage{

    public static final String URL = "#";
    private static final Logger LOG = LoggerFactory.getLogger(SpotifyLoginPage.class);
    WebDriverWait myWaitVar;

    @FindBy(how= How.ID,using = "login-username")
    private WebElement username;

    @FindBy(how= How.ID,using = "login-password")
    private WebElement password;

    @FindBy(how= How.ID,using = "login-button")
    private WebElement LoginButton;

    @FindBy(how= How.ID,using = "//span[contains(text(),'Spotify')]")
    private WebElement spotifyicon;

    @FindBy(how= How.ID,using = "//button[@class='mh-header-primary svelte-1uykb4d']")
    private WebElement profileicon;
    //button[@class='mh-header-primary svelte-1uykb4d']



    public SpotifyLoginPage(WebDriver driver) {
        super(driver);
        myWaitVar = new WebDriverWait(driver, 20);
    }

    public void inputUsername(String name){
        if(DriverUtil.isChrome()){
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("login-username")));
        }
        username.clear();
        username.sendKeys();
        LOG.info("Username is : "+name);
        pause(2000);
    }

    public void inputPassword(String pass){
        if(DriverUtil.isChrome()){
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("login-password")));
        }
        username.clear();
        username.sendKeys();
        LOG.info("Username is : "+pass);
        pause(2000);
    }

    public void ClickConnectButton(){
        LoginButton.click();
        pause(2000);
    }

    public void CheckIfAccountType(){
        if(DriverUtil.isChrome()){
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Spotify')]")));
        }
        Assert.assertTrue("Spotify".equals(spotifyicon));
        Assert.assertTrue("Profile".equals(profileicon));


    }









    public void close() { this.driver.close(); }
}
