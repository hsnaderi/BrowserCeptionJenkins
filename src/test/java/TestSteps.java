import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by haroonnaderi on 6/16/17.
 */
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features"
//)
public class TestSteps {

    WebDriver driver;
    public static final String USERNAME = System.getenv("BSUSERNAME");
    public static final String AUTOMATE_KEY = System.getenv("BSKEY");
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Given("^Setting up the RemoteWebDriver for Chrome$")
    public void settingUpChromeBrowser() throws Throwable{
        System.out.println("chrome starting up");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "Browserception");
        caps.setCapability("project", "tech onboarding 1");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @Given("^Setting up the RemoteWebDriver for Safari$")
    public void settingUpSafariBrowser() throws Throwable{
        System.out.println("safari starting up");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Safari");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "Browserception");
        caps.setCapability("project", "tech onboarding 1");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @Given("^Setting up the RemoteWebDriver for Firefox$")
    public void settingUpFirefoxBrowser() throws Throwable{
        System.out.println("firefox starting up");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "Browserception");
        caps.setCapability("project", "tech onboarding 1");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @When("^Testing BrowserStack Live via BrowserStack automate$")
    public void testing_BrowserStack_Live_via_BrowserStack_automate() throws Throwable{
        System.out.println("doing the actual test rn");
        driver.get("http://www.browserstack.com");

        try { driver.findElement(By.xpath("//a[contains(@href, '/users/sign_in')]")).click();}
        catch (ElementNotVisibleException e){
            driver.findElement(By.id("primary-menu-toggle")).click();
            driver.findElement(By.xpath("//a[contains(@href, '/users/sign_in')]")).click();
        }

        Thread.sleep(5000);
        driver.findElement(By.id("user_email_login")).sendKeys(System.getenv("BROWSERSTACKUSERNAME"));
        driver.findElement(By.id("user_password")).sendKeys(System.getenv("BROWSERSTACKPASSWORD"));
        driver.findElement(By.id("user_submit")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("installExtension")));
        Thread.sleep(10000);

        try { driver.findElement(By.id("skip-local-installation")).click(); }
        catch (NoSuchElementException e)
        { }
        driver.findElement(By.className("rf-win10-os-ico")).click();
        driver.findElement(By.xpath("//li[contains(@data-named-version, 'win10_chrome_latest')]")).click();
        Thread.sleep(25000);

        driver.switchTo().activeElement().sendKeys("BrowserStack");
        driver.switchTo().activeElement().sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }


    @Then("^Closing things up$")
    public void closing_things_up() throws Throwable{
        System.out.println("closing out");
        driver.close();
        driver.quit();
    }



}
