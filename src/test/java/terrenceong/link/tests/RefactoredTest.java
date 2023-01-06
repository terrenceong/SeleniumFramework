package terrenceong.link.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import terrenceong.link.data.DataReader;
import terrenceong.link.pageobjects.*;
import terrenceong.link.testcomponent.BaseTest;
import terrenceong.link.testcomponent.RetryTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RefactoredTest extends BaseTest {
    //retry is added because this test is flaky
    @Test(dataProvider = "getData",groups = {"Purchase"},retryAnalyzer = RetryTest.class)
    public void submitOrder(HashMap<Object,Object> input) throws InterruptedException, IOException {
        System.out.println(input);
        List<String> shoppingList = (List<String>)input.get("shoppingItems");
        ProductCatalogue productCatalogue = loginPage.loginApplication(String.valueOf(input.get("email")),
                String.valueOf(input.get("password")));
        productCatalogue.addProductToCart(shoppingList);
        CartPage cartPage =  productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyCartItemsAreCorrect(shoppingList));
        CheckOutPage checkOutPage= cartPage.goToCheckOut();
        checkOutPage.selectCountry("United");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        Assert.assertTrue(confirmationPage.verifyConfirmation());
    }
    @Test(dependsOnMethods = "submitOrder")
    public void orderHistoryValidation() throws InterruptedException {
        String[] shoppingArr = {"ADIDAS ORIGINAL","ZARA COAT 3"};
        List<String> shoppingList = Arrays.asList(shoppingArr);
         ProductCatalogue p  =super.loginPage.loginApplication("yusarin@gmail.com","YusaxMisa123");
         OrderPage orderPage = p.goToOrder();
         orderPage.verifyOrderHistoryContainItem(shoppingList);
    }
    //hash map way
//    @DataProvider
//    public Object[][] getData(){
//        String[][] shoppingList = {{"ADIDAS ORIGINAL","ZARA COAT 3"},{"ADIDAS ORIGINAL","IPHONE 13 PRO"}};
//        HashMap<Object,Object> map = new HashMap<>();
//        map.put("email","yusarin@gmail.com");
//        map.put("password","YusaxMisa123");
//        map.put("shoppingItems",shoppingList[0]);
//        HashMap<Object,Object> map1 = new HashMap<>();
//        map1.put("email","bobby@gmail.com");
//        map1.put("password","Bobby123");
//        map1.put("shoppingItems",shoppingList[1]);
//        return new Object[][] {{map},{map1}};
//    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<Object,Object>> data = DataReader.getJsonDataToMap(System.getProperty("user.dir") +"\\src\\main\\java\\terrenceong\\link\\data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
