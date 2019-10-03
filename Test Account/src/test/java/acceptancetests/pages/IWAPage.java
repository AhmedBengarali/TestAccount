package acceptancetests.pages;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class IWAPage extends BasePage{
    public static final String URL="#";
    private static final Logger LOG = LoggerFactory.getLogger(IWAPage.class);
 
    public IWAPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.NAME, using = "USERNAME")
    private WebElement userInputField;
 
    @FindBy(how = How.NAME, using = "PASSWORD")
    private WebElement passwordInputField;
     
    @FindBy(how = How.ID, using = "loginButton")
    private WebElement loginButton;
     
    @FindBy(how = How.ID, using = "loginButton")
    private WebElement iwaLoginBtn;
                  
    public void enterName(String user) {
        userInputField.clear();
        LOG.debug("IWALogin: Sending User Name: "+user);
        userInputField.sendKeys(user);       
    }
     
    public void enterPassword(String password) {
        passwordInputField.clear();
        String anyomizedPassword = password.replaceAll(".", "*");
        LOG.debug("IWALogin: Sending Password: "+anyomizedPassword);
        passwordInputField.sendKeys(password);
    }
     
    public MyPGAOverviewPage clickLoginButton() {
        loginButton.click();
        LOG.debug("Clicking Login Button");
        return new MyPGAOverviewPage(driver);
    }
         
    public MyPGAOverviewPage clickIwaLoginButton() {
        iwaLoginBtn.click();
        return new MyPGAOverviewPage(driver);
    }
     
    public void close() {
        this.driver.close();
    }
}
