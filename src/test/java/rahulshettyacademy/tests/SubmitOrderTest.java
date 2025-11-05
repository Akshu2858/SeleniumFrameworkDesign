package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) {

        ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean verifyProductDisplay = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(verifyProductDisplay);
        CheckOutPage checkOutPage = cartPage.goToCheckout();

        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
        String confirmMessageText = confirmationPage.getConfirmMessage();

        Assert.assertTrue(confirmMessageText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void oderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApp("akshu16@gmail.com", "Akshu16@gmail.com");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","akshu16@gmail.com");
//        map.put("password","Akshu16@gmail.com");
//        map.put("product","ZARA COAT 3");

//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","rockstar16@gmail.com");
//        map1.put("password","Rockstar16@gmail.com");
//        map1.put("product","ADIDAS ORIGINAL");

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
    

    
    
}
