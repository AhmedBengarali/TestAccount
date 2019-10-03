package acceptancetests.pages;
 
import acceptancetests.base.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.Kernel;

public class MyPGAOverviewPage extends BasePage{

    public static final String URL="#";
    private static final Logger LOG = LoggerFactory.getLogger(MyPGAOverviewPage.class);
     
    @FindBy(how = How.ID, using = "btnNewBri")
    private WebElement createNewBRIButton;
     

    @FindBy(how = How.XPATH, using = "//a[@translate='CreateBRI.new_bri_popup_without_gripps']") ///html/body/modal-container/div/div/div[2]/div[2]/div[2]/a
    private WebElement createNewBRIWithoutGRIPPSReferenceLink;
     
    @FindBy(how = How.XPATH, using = "//a[@href=\"logout.fcc\"]")
    private WebElement logoutLink;
    
    @FindBy(how = How.ID, using = "logoutButton")
    private WebElement logoutButton;
     
    @FindBy(how = How.ID, using = "logoutButton")
    private WebElement iwaLogoutBtn;
    
      @FindBy(how = How.ID, using = "create_pga_button")
    private WebElement create_pga_button;

    @FindBy(how = How.ID,using = "search-input")
    private WebElement SearchInput;

    @FindBy(how = How.ID, using = "subProduct_input")
    private WebElement SubProductFilterField;



      
    public MyPGAOverviewPage(WebDriver driver) {
        super(driver);
         pause(5000);
    }
     
    public void clickCreateNewBRIButton() {
        LOG.debug("MyBRIOverviewPage: Clicking Create New BRI Button");
        createNewBRIButton.click();
    }
     
    public CreateNewPGAPage clickCreatNewBPGA() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(createNewBRIWithoutGRIPPSReferenceLink));
        createNewBRIWithoutGRIPPSReferenceLink.click();
        LOG.debug("Clicking New BRI Without Gripps Link");       
        return new CreateNewPGAPage(driver);
    }
     
    public boolean checkLogoutLink() {
        return (findById("logoutButton") != null) ;
    }
    public void clickLogoutButton() {
        if (DriverUtil.isChrome()) {
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("logoutButton")));
        }
        logoutButton.click();
       // return new IWAPage(driver);
    }
         
    public IWAPage clickIwaLogoutButton() {
        iwaLogoutBtn.click();
        return new IWAPage(driver);
    }
     
    public void close() { this.driver.close(); }

      public  CreateNewPGAPage clickCreatePGA() {
       create_pga_button.click();
       LOG.info("tocreate pga");
       return  new  CreateNewPGAPage(driver);
    }
    public SearchMyPGATable ClickOnSearchField(){
        SearchInput.click();
        LOG.info("to start search");
        return new SearchMyPGATable(driver);
    }

    public SearchMyPGATable ClickIntofilterField(){
        SubProductFilterField.click();
        LOG.info("start filtering by Sub-Product");
        return new SearchMyPGATable(driver);
    }



}