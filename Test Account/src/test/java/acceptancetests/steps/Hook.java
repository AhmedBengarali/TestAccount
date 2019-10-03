package acceptancetests.steps;
 
import acceptancetests.base.DriverUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
 
public class Hook {
     
    private static final Logger LOG = LoggerFactory.getLogger(Hook.class);
    @Before
    public void InitializeTest(Scenario scenario) throws MalformedURLException {
        LOG.info("=================================================");
        LOG.info("Starting Scenario: " + scenario.getName());
         
        //if the test is not marked as web test then no selenium driver is needed
        if (!(scenario.getSourceTagNames().contains("@web") || scenario.getSourceTagNames().contains("@WEB"))) {
            return;
        }

        RemoteWebDriver driver = (RemoteWebDriver)DriverUtil.initDriver(scenario);
         
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
         
        LOG.info("Video URL: " + DriverUtil.getSeleniumBoxUrl() +"/videos/" + driver.getSessionId() + ".mp4");
        LOG.info("Live View URL:  " + DriverUtil.getSeleniumBoxUrl() +"/ui/liveview?session=" + driver.getSessionId().toString());
         
    }
     
    @After
    public void TearDownTest(Scenario scenario) { 
        if (scenario.getSourceTagNames().contains("@web") || scenario.getSourceTagNames().contains("@WEB")) {         
            WebDriver driver = DriverUtil.getDriver();
            //Take screenshot logic goes here
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
            scenario.write("Screenshot attached");
        }
        if (scenario.isFailed()) {           
            LOG.info("SCENARIO '"+scenario.getName()+"' FAILED");
        } else {
            LOG.info("SCENARIO '"+scenario.getName()+"' PASSED");
        }
         
        DriverUtil.closeDriver();
         
    }
}
