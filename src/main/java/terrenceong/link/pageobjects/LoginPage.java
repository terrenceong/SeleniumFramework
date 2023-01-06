package terrenceong.link.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import terrenceong.link.abstractcomponent.EssentialComponent;

public class LoginPage extends EssentialComponent {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="userEmail")
    private WebElement email;
    @FindBy(xpath="//input[@formcontrolname='userPassword']")
    private WebElement password;
    @FindBy(css = "input#login")
    private WebElement login;

    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    private WebElement error;

    public ProductCatalogue loginApplication(String email,String password){
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.login.click();
        return new ProductCatalogue(driver);
    }

    public String getFailedLoginErrorMessage(){
        super.waitForElementToAppear(this.error);
        return this.error.getText();
    }
    public void goTo(){
        this.driver.get("https://rahulshettyacademy.com/client");
    }
}
