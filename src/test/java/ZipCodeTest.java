import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkZipCode4digits() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("zip_code")));
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
    }

    @Test
    public void CheckZipCodeValidTest() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("zip_code")));
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));

        driver.findElement(By.name("first_name")).sendKeys("Timofey");
        driver.findElement(By.name("last_name")).sendKeys("Filin");
        driver.findElement(By.name("email")).sendKeys("tfilin888@ya.ru");
        driver.findElement(By.name("password1")).sendKeys("Password123!");
        driver.findElement(By.name("password2")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("[value = 'Register']")).click();
    }
}