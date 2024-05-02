package dusanselenium.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import dusanselenium.pageobjects.CartPage;
import dusanselenium.pageobjects.ProductsPage;
import dusanselenium.testcomponents.BaseTest;
import dusanselenium.testcomponents.Retry;

public class ErrorValidations extends BaseTest {

	@Test(groups = { "Error Handling" }, retryAnalyzer = Retry.class)
	public void loginIncorrectCredentials() {
		landingPage.loginToApplication("duleselenium@gmail.com", "D.Simic19955!");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorText());
	}

	@Test(groups = { "Error Handling" })
	public void productErrorValidation() {
		String productName = "ZARA COAT 3";
		ProductsPage productsPage = landingPage.loginToApplication("duleselenium2@gmail.com", "D.Simic1995!");
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(productName);
		CartPage cartPage = productsPage.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
