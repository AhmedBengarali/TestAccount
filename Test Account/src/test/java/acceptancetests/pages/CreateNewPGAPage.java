package acceptancetests.pages;

import acceptancetests.base.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saidiz
 */
public class CreateNewPGAPage extends BasePage {

    public static final String URL = "#";
    private static final Logger LOG = LoggerFactory.getLogger(CreateNewPGAPage.class);
    WebDriverWait myWaitVar;
    @FindBy(how = How.ID, using = "create_pga_btn")
    private WebElement create_pga_btn;

    @FindBy(how = How.ID, using = "evaluation_name_input")
    private WebElement PGAname;

    @FindBy(how = How.ID, using = "evaluation_scope_input")
    private WebElement ScopeName;

    @FindBy(how = How.ID, using = "product_input")
    private WebElement product_input;

    @FindBy(how = How.XPATH, using = "//*[@id=\"product_dropdown\"]/ul/div/ul/div/li/div/span")
    private WebElement App;


    public CreateNewPGAPage(WebDriver driver) {
        super(driver);
        myWaitVar = new WebDriverWait(driver, 20);
    }

    public void close() {
        this.driver.close();
    }

    public void clickSavePGA() {

        create_pga_btn.click();
        pause(2000);
    }

    public void pgaName(String name) {

        if (DriverUtil.isChrome()) {
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("evaluation_name_input")));
        }
        PGAname.clear();
        PGAname.sendKeys(name);
        LOG.debug("set  Name as : " + name);
        pause(1000);
    }
    public void Scopename(String nameS){
        if(DriverUtil.isChrome()){
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"evaluation_scope_input\"]")));
        }
        ScopeName.clear();
        ScopeName.sendKeys(nameS);
        LOG.info("set Scope name as:"+nameS );
        pause(1000);
    }

    public void productName(String product) throws InterruptedException {

        if (DriverUtil.isChrome()) {
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.id("product_input")));
        }
        product_input.clear();
        product_input.sendKeys(product);
        product_input.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        LOG.debug("set  product as: " + product);
        pause(1000);
    }
    public void AppSelect (String app ) throws InterruptedException {
        if (DriverUtil.isChrome()) {
            waitForElement(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"product_dropdown\"]/ul/div/ul/div/li/div/span")));
        }
        App.clear();
        App.sendKeys(app);
        App.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        LOG.debug("set  Application as: " + app);
        pause(1000);
    }
}
//*[@id="product_dropdown"]/ul/div/ul/div/li/div/span