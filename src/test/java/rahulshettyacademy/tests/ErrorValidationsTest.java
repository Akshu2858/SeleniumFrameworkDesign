package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.util.List;

//167 lecture
public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginError() {
        landingPage.loginApp("akshu16@gmail.com", "Akshu16@@gmail.com"); //gave wrong password
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Incorrect email password."); //removed "or"
    }

    @Test
    public void productError() {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApp("srusti@gmail.com", "Srusti1@gmail.com");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean verifyProductDisplay = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(verifyProductDisplay);
    }

}
