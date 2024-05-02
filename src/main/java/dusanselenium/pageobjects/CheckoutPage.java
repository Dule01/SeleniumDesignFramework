package dusanselenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dusanselenium.abstractcomponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder=\"Select Country\"]")
	WebElement countryElement;

	@FindBy(css = ".ta-item")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	By countryList = By.cssSelector(".ta-results");

	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(countryElement, country).build().perform();
		waitForElementToAppear(countryList);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}

}
