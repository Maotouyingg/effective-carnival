import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;


public class ZipCodeTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testInvalidZipCode_4digits() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        boolean stillOnZipPage = driver.findElement(By.name("zip_code")).isDisplayed();
        Assert.assertTrue(stillOnZipPage, "При неверном zip code должны остаться на той же странице");
    }

    @Test
    public void testValidZipCode_5digits() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
        boolean onRegistrationPage = driver.findElement(By.name("first_name")).isDisplayed();
        Assert.assertTrue(onRegistrationPage, "Должны перейти на страницу регистрации");
    }

    @Test
    public void testCompleteRegistrationProcess() {
        testValidZipCode_5digits();
        driver.findElement(By.name("first_name")).sendKeys("Timofey");
        driver.findElement(By.name("last_name")).sendKeys("Filin");
        driver.findElement(By.name("email")).sendKeys("tfilin888@ya.ru");
        driver.findElement(By.name("password1")).sendKeys("Password123!");
        driver.findElement(By.name("password2")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("[value='Register']")).click();
        List<WebElement> accountMessages = driver.findElements(
                By.xpath("//*[contains(text(), 'account') or contains(text(), 'Account')]"));
        boolean success = !accountMessages.isEmpty();
        Assert.assertTrue(success, "Должно быть сообщение об аккаунте после регистрации");
    }

    @Test
    public void testEmptyZipCode() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        boolean stillOnZipPage = driver.findElement(By.name("zip_code")).isDisplayed();
        Assert.assertTrue(stillOnZipPage, "При пустом zip code должны остаться на странице");
    }
}