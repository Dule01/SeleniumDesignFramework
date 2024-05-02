package dusanselenium.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.dsig.XMLObject;

import org.checkerframework.checker.signature.qual.BinaryNameWithoutPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dusanselenium.pageobjects.CartPage;
import dusanselenium.pageobjects.CheckoutPage;
import dusanselenium.pageobjects.ConfirmationPage;
import dusanselenium.pageobjects.LandingPage;
import dusanselenium.pageobjects.OrdersPage;
import dusanselenium.pageobjects.ProductsPage;
import dusanselenium.testcomponents.BaseTest;

public class StandaloneTest extends BaseTest {
	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductsPage productsPage = landingPage.loginToApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(input.get("productName"));
		CartPage cartPage = productsPage.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
		checkoutPage.selectCountry("Yugoslavia");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductsPage productsPage = landingPage.loginToApplication("duleselenium@gmail.com", "D.Simic1995!");
		OrdersPage ordersPage = landingPage.navigateToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrdersDisplay("ZARA COAT 3"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\dusanselenium\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
