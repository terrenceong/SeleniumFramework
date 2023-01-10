package terrenceong.link.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import terrenceong.link.pageobjects.CartPage;
import terrenceong.link.pageobjects.ProductCatalogue;
import terrenceong.link.testcomponent.BaseTest;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ErrorValidationTest extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() throws IOException {
        ProductCatalogue productCatalogue = loginPage.loginApplication("123@gmail.com","123");
        Assert.assertEquals("Incorrect email or password.",loginPage.getFailedLoginErrorMessage());

    }
    @Test(groups = {"ErrorHandling"})
    public void cartErrorValidation(){
        String[] shoppingArr = {"ADIDAS ORIGINAL","ZARA COAT 33"};
        List<String> shoppingList = Arrays.asList(shoppingArr);
        ProductCatalogue productCatalogue = loginPage.loginApplication("yusarin@gmail.com","YusaxMisa123");
        productCatalogue.addProductToCart(shoppingList);
        CartPage cartPage = productCatalogue.goToCart();
        Assert.assertFalse(cartPage.verifyCartItemsAreCorrect(shoppingList));

    }

}
