package acceptancetest.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void WaitForPageToLoad(WebDriver driver){

        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
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

    public String getTitle() {

        return driver.getTitle();
    }






    public void waitForElement(ExpectedCondition<WebElement> element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(element);
    }







    public void pause(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }
}
