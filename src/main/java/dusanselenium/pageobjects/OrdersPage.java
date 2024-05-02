package dusanselenium.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dusanselenium.abstractcomponent.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsAdded;
	
	public Boolean verifyOrdersDisplay(String productName) {
		Boolean match = productsAdded.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
