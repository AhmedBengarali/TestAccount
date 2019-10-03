package acceptancetests.pages;

import acceptancetests.base.DriverUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

/**
 * This is the base class for page objects. All common elements (e.g. menues,
 * footers etc.), used by multiple pages of the target application, can be
 * defined here.
 *
 * @author q271820
 */
public class BasePage {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriver driver;

    @FindBy(how = How.ID, using = "intial_coverage_link")
    private WebElement btnRating;
    @FindBy(how = How.ID, using = "myprofile")
    private WebElement checkxProfil;
    @FindBy(how = How.ID, using = "projects_link")
    private WebElement btnAssessment;

    @FindBy(how = How.ID, using = "home")
    private WebElement homeIcon;

    @FindBy(how = How.XPATH, using = "//*[@id=\"settings\"]")
    private WebElement btnSettings;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/router-outlet/ngx-spinner")
    private WebElement spinner;

    @FindBy(how = How.XPATH, using = "//*[@id=\"projects_link\"]")
    private WebElement btnProjectSelection;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForSpinnerToComplete() {
        try {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.and(ExpectedConditions.invisibilityOf(spinner)));
         } catch (TimeoutException e) {
            LOG.error(String.format("Waitning for spinner failed", e));
        }
    }

    public void waitForElement(ExpectedCondition<WebElement> element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(element);
    }

    public WebElement findByText(String searchText) {
        waitForElement(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + searchText + "')]")));
        return driver.findElement(By.xpath("//*[contains(text(), '" + searchText + "')]"));
    }

    public WebElement findByClass(String classString) {
        try {
            List<WebElement> elements = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(classString)));
            if(elements.size() > 0 ){
                return elements.get(0);
            }
        } catch (TimeoutException | NoSuchElementException e) {
            LOG.error(String.format("Element with class name: %s  not found", classString));
        }
        return null;        
    }

    public WebElement findById(String id) {
        try {
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        return element;
        } catch(TimeoutException e) {
            LOG.info("Element not found "+id);
        }
        return null ;        
    }
    
    public WebElement findVisibleById(String id) {
        try {
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return element;
        } catch(TimeoutException e) {
            LOG.info("Element not found "+id);
        }
        return null ;        
    }
    
    public List<WebElement> findByXpath(String xpath) {
        try {
            return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch(TimeoutException e) {
            LOG.info("Elements not found");
        }
        return new ArrayList<WebElement>();

    }
    
    public List<WebElement> findElementsById(String id) {
        try {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(id)));
        } catch(TimeoutException e) {
            LOG.info("Elements not found");
        }
        return new ArrayList<WebElement>();
    }
    
    public String getTitle() {
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(BasePage.class.getName()).log(Level.SEVERE, null, ex);
//        }

  //      waitForSpinnerToComplete();
        return driver.getTitle();
    }

    public boolean checkIfCheckboxChecked(String elementId) {
        WebElement element = findById(elementId);
        boolean checked =(element != null && element.isSelected());
        return (checked);
    }

    public boolean checkIfRadioChecked(String id) {
        String str = driver.findElement(By.id(id)).getAttribute("checked");
        return str.equalsIgnoreCase("true");
    }

    public boolean checkIfInputIsfilledOut(String elementId) {
        WebElement projectsLink = driver.findElement(By.id(elementId));
        boolean filledout = projectsLink.getAttribute("value").isEmpty();
        return (filledout);
    }

    public boolean elementHasClass(WebElement element, String active) {
        return element.getAttribute("class").contains(active);
    }

    public void inputWriteRandomCharacters(String id) {
        String uuid = UUID.randomUUID().toString();
        driver.findElement(By.id(id)).sendKeys(uuid);
    }

    public void pause(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }
}
