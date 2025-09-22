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
    public void checkZipCode4digits(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip-code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorText = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error_message")));
        String errorMessage = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits\n");
        driver.quit();
    }
    @Test
    public void checkZipCode5digits(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip-code")).sendKeys("12334");
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        WebDriverWait waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorText = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error_message")));
        String errorMessage = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits\n");
        driver.quit();
    }

}
