import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(RegistrationFormReport.class)
public class RegistrationFormTest3 {

    WebDriver driver;

    @BeforeMethod

    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\hi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaregform.ccbp.tech/");
    }

   @DataProvider(name = "registrationData")

    public Object[][] getRegistrationData(){
        return  new Object[][]{
                {"Emily", "Thompson"},
                {"James", "Rodriguez"},
                {"Sarah", "Mitchell"},
                {"Michael", "Harris"},
                {"Olivia","Carter"}

        };

    }

    @Test(dataProvider = "registrationData")
    public void  testRegisterWithValidCreds(String firstName, String lastName){

        WebElement firstNameEl = driver.findElement(By.id("firstName"));
        firstNameEl.sendKeys(firstName);

        WebElement lastNameEl = driver.findElement(By.id("lastName"));
        lastNameEl.sendKeys(lastName);

        WebElement submitButtonEl = driver.findElement(By.className("submit-button"));
        submitButtonEl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMsgEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Submitted Successfully']")));
        String expectedSuccessMsg = successMsgEl.getText();
        String actualSuccessMsg = "Submitted Successfully";

        Assert.assertEquals(expectedSuccessMsg,actualSuccessMsg,"Success message do not match");

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
