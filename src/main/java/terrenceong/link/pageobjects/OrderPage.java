package terrenceong.link.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import terrenceong.link.abstractcomponent.EssentialComponent;

import java.util.List;
import java.util.stream.Collectors;

public class OrderPage extends EssentialComponent {
    private WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//table[contains(@class,'table-bordered')] //tr[@class='ng-star-inserted']/td[2]")
    List<WebElement> items;
    public boolean verifyOrderHistoryContainItem(List<String> shoppingList) throws InterruptedException {
        Thread.sleep(2000L);
        List<String> itemHistoryList = this.items.stream().map(item->item.getText().toUpperCase()).collect(Collectors.toList());
        for(String item:shoppingList){
            if(!itemHistoryList.contains(item))
                return false;
        }
        return true;
    }
}
