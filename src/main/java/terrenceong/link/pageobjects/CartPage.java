package terrenceong.link.pageobjects;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import terrenceong.link.abstractcomponent.EssentialComponent;

import java.util.List;

public class CartPage extends EssentialComponent {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='cartSection']/h3")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement checkOutBtn;

    private By cartSection = By.xpath("//div[@class='cartSection']/h3");

    public List<WebElement> getCartItems(){
        super.waitForElementToAppear(cartSection);
        return this.cartItems;
    }

    public boolean verifyCartItemsAreCorrect(List<String> shoppingList){
        if(shoppingList.size()!=getCartItems().size())
            return false;
        for(WebElement item:getCartItems()){
            if(!shoppingList.contains(item.getText())){
                return false;
            }
        }
        return true;
    }
    public CheckOutPage goToCheckOut() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(3000);
        this.checkOutBtn.click();
        return new CheckOutPage(this.driver);
    }


}
