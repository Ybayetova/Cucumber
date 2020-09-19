package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditOrderPage {

   WebDriver driver = Driver.getDriver("browser");

   public EditOrderPage(){
       PageFactory.initElements(driver, this);
   }

   @FindBy (id="ctl00_MainContent_fmwOrder_txtQuantity")
   public WebElement quantityBox;

    @FindBy(name = "ctl00$MainContent$fmwOrder$txtName")
    public WebElement customerNameBox;

    @FindBy(name ="ctl00$MainContent$fmwOrder$TextBox2")
    public WebElement streetBox;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox3")
    public WebElement cityBox;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox4")
    public WebElement stateBox;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox5")
    public WebElement zipBox;

    @FindBy(id="ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement VisaCardButton;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox6")
    public WebElement cardNumBox;

    @FindBy (name="ctl00$MainContent$fmwOrder$TextBox1")
    public WebElement expDateBox;

    @FindBy (id="ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy (tagName = "strong")
    public WebElement verifiedOrderCreated;

    @FindBy (xpath = "//label[@for='ctl00_MainContent_fmwOrder_ddlProduct']")
    public WebElement productLabel;

    @FindBy (xpath = "//label[@for='ctl00_MainContent_fmwOrder_txtQuantity']")
    public WebElement productQuantityLabel;

    @FindBy (xpath = "//label[@for='ctl00_MainContent_fmwOrder_txtUnitPrice']")
    public WebElement productPriceLabel;

    @FindBy (xpath = "//label[@for='ctl00_MainContent_fmwOrder_txtDiscount']")
    public WebElement productDiscountLabel;

    @FindBy (xpath = "//label[@for='ctl00_MainContent_fmwOrder_txtTotal']")
    public WebElement productTotalLabel;


}
