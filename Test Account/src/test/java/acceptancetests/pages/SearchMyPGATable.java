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
import org.testng.Assert;

import java.util.List;

public class SearchMyPGATable extends BasePage {


    public static final String Url="#";
    private static final Logger LOG = LoggerFactory.getLogger(SearchMyPGATable.class);
    WebDriverWait myWaitVar;

    @FindBy(how = How.ID,using = "search-input")
    private WebElement SearchInput;

    @FindBy(how = How.ID,using = "filter_button")
    private WebElement FilterButton;

    @FindBy(how = How.ID, using = "subProduct_input")
    private WebElement SubProductFilterField;

    @FindBy(how = How.XPATH, using ="//body//tr[2]" )
    private List<WebElement> object;


    public SearchMyPGATable(WebDriver driver) {
        super(driver);
        myWaitVar = new WebDriverWait(driver, 20);
    }

    public void close() { this.driver.close(); }

    public void ClickIntoSearchField(){
        SearchInput.click();
        SearchInput.clear();
        pause(2000);
    }
    public void searchkey(String pganame){
        if (DriverUtil.isChrome()) {
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("search-input")));
        }
        SearchInput.clear();
        SearchInput.sendKeys(pganame);
        LOG.debug("search for : "+pganame);
       pause(300);
    }
    public void submitsearch(){
        SearchInput.sendKeys(Keys.ENTER);
        pause(2000);

    }

    public void checkresult(){
        for(int i =0 ; i < 5 ; i++){


        try {
            Assert.assertEquals(object.size(),1);
            LOG.info("Number of object found  : "+object.size());
            break;
        }catch (Exception e){
            if(i != 4 ){
                continue;
            }else {
                throw new RuntimeException("Cannot found exact result");
            }
        }
        }

        }

        public void ActivateFilter(){
        FilterButton.click();
        }
        public void subProductvalue(String name){
            if (DriverUtil.isChrome()) {
                waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("subProduct_input")));
            }
            SubProductFilterField.clear();
            SubProductFilterField.sendKeys(name);
            LOG.debug("Entering Sub-Product as : "+name);
            pause(300);
        }




}
