package terrenceong.link.pageobjects;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import terrenceong.link.abstractcomponent.EssentialComponent;

import java.util.List;

public class CheckOutPage extends EssentialComponent {
    private WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryField;

    @FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
    private List<WebElement> selectCountry;
    @FindBy(css="div.actions a")
    private WebElement placeOrderBtn;
    private By countryFieldLocator = By.xpath("//input[@placeholder='Select Country']");

    private By countryFieldSuggestionLocator = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");


    public void selectCountry(String text){
        super.waitForElementToAppear(this.countryFieldLocator);
        this.countryField.sendKeys(text);
        super.waitForElementToAppear(this.countryFieldSuggestionLocator);
        for(WebElement country:this.selectCountry){
            if(country.getText().equalsIgnoreCase("United States")){
                country.click();
                break;
            }
        }
    }
    public ConfirmationPage submitOrder(){
        this.placeOrderBtn.click();
        return new ConfirmationPage(this.driver);
    }

}
