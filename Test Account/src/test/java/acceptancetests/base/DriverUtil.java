package acceptancetests.base;

import cucumber.api.Scenario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper Class for reading the test automation properties
 * and for providing the Webdriver
 */
public final class DriverUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DriverUtil.class);
    private static final String confFile ="src/test/resources/testautomation.properties";

    private static WebDriver driver;
    private static String seleniumBoxUrl;
    private static String seleniumBoxToken;
    private static String targetWebUIUrl;
    private static String targetRestApiUrl;


    private static String browser = "chrome";
    public static long DEFAULT_WAIT = 20;


    static {
        loadSystemProperties();

        // initialize the Selenium Box Url
        seleniumBoxUrl = System.getProperty("testautomation.seleniumBoxUrl");


        // initialize the Selenium Box token
        seleniumBoxToken = System.getProperty("testautomation.seleniumBoxToken");


        // initialize the UI Url
        targetWebUIUrl = System.getProperty("testautomation.targetWebUIUrl");

        // initialize the REST API Url
        targetRestApiUrl = System.getProperty("testautomation.targetRestApiUrl");


        // initialize which browser to use
        browser = System.getProperty("testautomation.browser");
        if ("".equals(browser) || browser == null) {
            LOG.error("The browser was not specified in " + confFile);
            System.exit(0);
        }
        // here the browser can be overwritten with the one defined in Jenkins for example
        String envbrowser = System.getenv("BROWSER");
        if (!"".equals(envbrowser) && envbrowser != null) {
            browser = envbrowser;
        }
    }

    private DriverUtil() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getTargetWebUrl() {
        if(targetWebUIUrl == null || targetWebUIUrl.isEmpty()) {
            LOG.error("The UI url of the target test application was not specified or is empty in " + confFile);
            System.exit(0);
        }

        return targetWebUIUrl;
    }
    public static String getTargetRestUrl() {
        if(targetRestApiUrl == null || targetRestApiUrl.isEmpty()) {
            LOG.error("The REST API url of the target test application was not specified or is empty in " + confFile);
            System.exit(0);
        }
        return targetRestApiUrl;
    }

    private static String getSeleniumBoxToken() {
        if(seleniumBoxToken == null || seleniumBoxToken.isEmpty()) {
            LOG.error("The Selenium Box token was not specified or is empty in " + confFile);
            System.exit(0);
        }
        return seleniumBoxToken;
    }

    public static String getSeleniumBoxUrl() {
        if(seleniumBoxUrl == null || seleniumBoxUrl.isEmpty()) {
            LOG.error("The Selenium Box URL was not specified or is empty in " + confFile);
            System.exit(0);
        }
        return seleniumBoxUrl;
    }

    public static String getBrowser() {
        return browser;
    }


    public static WebDriver initDriver(Scenario scenario) {

        String buildnr = System.getenv("BUILD_NUMBER");
        if ("".equals(buildnr) || buildnr == null) {
            buildnr = "" + System.currentTimeMillis();
        }

        DesiredCapabilities capabilities = null;
        capabilities = getDesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("browserName", DriverUtil.getBrowser());
        capabilities.setCapability("e34:l_testName", scenario.getName());
        capabilities.setCapability("e34:video", true);
        capabilities.setCapability("e34:token", getSeleniumBoxToken());
        capabilities.setCapability("build", "Build-" + buildnr);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

				LOG.info("Start instanciate RemoteWebDriver object");
        try {
            driver = new RemoteWebDriver(new URL(getSeleniumBoxUrl()+"/wd/hub"), capabilities);
        } catch (MalformedURLException ex) {
            LOG.error(ex.getMessage());
            System.exit(0);
        }
        LOG.info("End instanciate RemoteWebDriver object");
        driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static DesiredCapabilities getDesiredCapabilities(){
        DesiredCapabilities ret = null;

        String preferredDriver = getBrowser().toLowerCase();

        switch (preferredDriver.toLowerCase()) {
            case "safari":
                ret = DesiredCapabilities.safari(); break;
            case "msie":
                ret = DesiredCapabilities.internetExplorer(); break;
            case "edge":
                ret = DesiredCapabilities.edge(); break;
            case "chrome":
                ret = DesiredCapabilities.chrome(); break;
            default:
                ret = DesiredCapabilities.firefox(); break;

        }
        return ret;
    }

    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
            } catch (NoSuchMethodError | NoSuchSessionException | SessionNotCreatedException ex) {
                LOG.error("An error occured while closing the driver: ", ex);
            }
        }
    }

    private static void loadSystemProperties(){
        try{
            File file = new File(confFile);
            FileInputStream fileInput = new FileInputStream(file);
            Properties sysProperties = new Properties();
            sysProperties.load(fileInput);

            Enumeration keys = sysProperties.keys();
            while(keys.hasMoreElements()){
                String key = (String) keys.nextElement();
                String value = sysProperties.getProperty(key);
                System.setProperty(key,value);
            }

        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static boolean isFirefox() {
        if (driver==null)
            return false;

        return "firefox".equals(getBrowser().toLowerCase());
    }

    public static boolean isChrome() {
        if (driver==null)
            return false;

        return "chrome".equals(getBrowser().toLowerCase());
    }

    public static boolean isMsIe() {
        if (driver==null)
            return false;

        return "msie".equals(getBrowser().toLowerCase());
    }

    public static boolean isEdge() {
        if (driver==null)
            return false;

        return "edge".equals(getBrowser().toLowerCase());
    }
}
