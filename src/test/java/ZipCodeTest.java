import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
public class ZipCodeTest {
    @Test
    public void checkZipCode4digits() {
        {

            {
                //System.setProperty("webdriver.chrome.driver", "timofeyfilin@MacBook-Air-Timofej Google Chrome.app");
                WebDriver driver = new ChromeDriver();
                driver.get("https://www.sharelane.com/cgi-bin/register.py");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("zip_code")));
                driver.findElement(By.name("zip_code")).sendKeys("1234");
                driver.findElement(By.cssSelector("[value = 'Continue']")).click();

            }
        }
    }
    @Test

    public void CheckZipCodeValidTest(){}

    public void setUp() {
        ChromeDriver driver = new ChromeDriver();
        {
            driver.findElement(By.name("zip_code")).sendKeys("12345");
            driver.findElement(By.cssSelector("[value = 'Continue']")).click();
            driver.findElement(By.name("first_name")).sendKeys("Timofey");
            driver.findElement(By.name("last_name")).sendKeys("Filin");
            driver.findElement(By.name("email")).sendKeys("tfilin888@ya.ru");
            driver.findElement(By.name("password1")).sendKeys("Password123!");
            driver.findElement(By.name("password2")).sendKeys("Password123!");
            driver.findElement(By.cssSelector("[value = 'Register']")).click();
        }
    }
}
