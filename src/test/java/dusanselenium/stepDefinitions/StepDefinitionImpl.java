package dusanselenium.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import dusanselenium.pageobjects.CartPage;
import dusanselenium.pageobjects.CheckoutPage;
import dusanselenium.pageobjects.ConfirmationPage;
import dusanselenium.pageobjects.LandingPage;
import dusanselenium.pageobjects.ProductsPage;
import dusanselenium.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductsPage productsPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password) {
		productsPage = landingPage.loginToApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productsPage.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.navigateToCheckoutPage();
		checkoutPage.selectCountry("Yugoslavia");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on the Confirmation Page")
	public void Message_is_displayed_on_the_Confirmation_Page(String message) {
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void Incorrect_message_is_displayed(String errorMsg) {
		Assert.assertEquals(errorMsg, landingPage.getErrorText());
		driver.close();
	}

}
