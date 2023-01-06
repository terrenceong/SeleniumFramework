package terrenceong.link.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import terrenceong.link.abstractcomponent.EssentialComponent;

public class ConfirmationPage extends EssentialComponent {
     private WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//h1[@class='hero-primary']")
    private WebElement confirmationText;

    private By confirmationTextLocator = By.xpath("//h1[@class='hero-primary']");

    public boolean verifyConfirmation(){
        super.waitForElementToAppear(this.confirmationTextLocator);
        return this.confirmationText.getText().equals("THANKYOU FOR THE ORDER.");
    }

}
