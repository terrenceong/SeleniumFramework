package terrenceong.link.abstractcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import terrenceong.link.pageobjects.CartPage;
import terrenceong.link.pageobjects.OrderPage;

import java.time.Duration;

public class EssentialComponent {
    private WebDriver driver;
    private WebDriverWait wait;

    public EssentialComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="[routerlink*='cart']")
    private WebElement cart;

    @FindBy(css = "[routerlink*='myorders']")
    private WebElement order;

    public void waitForElementToAppear(By findBy){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToAppear(WebElement element){
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToDisappear(By findBy){
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    public CartPage goToCart(){
        this.waitForElementToAppear(cart);
        this.cart.click();
        return new CartPage(this.driver);
    }
    public OrderPage goToOrder(){
        this.waitForElementToAppear(order);
        this.order.click();
        return new OrderPage(this.driver);
    }
}
