package terrenceong.link.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import terrenceong.link.pageobjects.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        String[] shoppingArr = {"ADIDAS ORIGINAL","ZARA COAT 3"};
        List<String> shoppingList = Arrays.asList(shoppingArr);
        webDriver.get("https://rahulshettyacademy.com/client");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriver.findElement(By.id("userEmail")).sendKeys("yusarin@gmail.com");
        webDriver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).sendKeys("YusaxMisa123");
        webDriver.findElement(By.cssSelector("input#login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));
        List<WebElement> products =
                webDriver.findElements(
                        By.xpath("//div[@class='card-body']"));
        List<WebElement> prod =
                products.stream().filter(element->shoppingList.contains(element.findElement(By.cssSelector("b"))
                        .getText())).collect(Collectors.toList());
        prod.forEach(item->
        {
            System.out.println(item.findElement(By.cssSelector("b")).getText());
            item.findElement(By.cssSelector("button:last-of-type")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        webDriver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']")));
        List<WebElement> cartItems = webDriver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        SoftAssert softAssert = new SoftAssert();
        cartItems.forEach(item-> softAssert.assertTrue(shoppingList.contains(item.getText())));
        softAssert.assertAll();


        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//button[text()='Checkout']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
        webDriver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("United");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
        List<WebElement> searchCountries = webDriver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
        for(WebElement country:searchCountries){
            if(country.getText().equalsIgnoreCase("United States")){
                country.click();
                break;
            }
        }
        webDriver.findElement(By.cssSelector("div.actions a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
        Assert.assertTrue(webDriver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
}
