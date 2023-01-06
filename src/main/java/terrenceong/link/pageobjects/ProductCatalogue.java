package terrenceong.link.pageobjects;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import terrenceong.link.abstractcomponent.EssentialComponent;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogue extends EssentialComponent {
    private WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='card-body']")
    private List<WebElement> products;
    private By productsBy = By.xpath("//div[@class='card-body']");
    private By addToCart = By.cssSelector("button:last-of-type");

    private By animation = By.cssSelector(".ng-animating");

    private By toast  =By.id("toast-container");
    public List<WebElement> getProductList(){
        super.waitForElementToAppear(this.productsBy);
        return this.products;
    }
    public List<WebElement> getProductsByNames(List<String> shoppingList){
        return getProductList().stream().filter(element->shoppingList.contains(element.findElement(By.cssSelector("b"))
                        .getText())).collect(Collectors.toList());
    }
    public void addProductToCart(List<String> shoppingList){
        getProductsByNames(shoppingList).forEach(item->
        {
            item.findElement(this.addToCart).click();
            super.waitForElementToDisappear(this.animation);
            super.waitForElementToAppear(this.toast);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
