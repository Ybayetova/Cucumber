package StepDefinition;

import Pages.EditOrderPage;
import Pages.WebOrdersHomePage;
import Pages.WebOrdersLogInPage;
import Utilities.CommonUtils;
import Utilities.Driver;
import Utilities.ExcelUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebOrdersAppSteps {

    WebDriver driver = Driver.getDriver("chrome");
    WebOrdersLogInPage webOrdersLogInPage = new WebOrdersLogInPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    EditOrderPage editOrderPage = new EditOrderPage();


    @Given("User navigates to WebOrders application")
    public void user_navigates_to_web_orders_application() {

        driver.get(CommonUtils.getProperty("WebOrdersURL"));

    }

    @When("User provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLogInPage.logIn(username,password);
    }

    @Then("User validates that application {string} logged in")
    public void user_validates_that_application_is_logged_in(String condition) {
        if(condition.equalsIgnoreCase("is")) {
            String expectedTitle = "Web Orders";
            String actualTitle = driver.getTitle();
            Assert.assertEquals("Actual Title " + actualTitle +
                    " Didn't match with expected Title: " + expectedTitle, expectedTitle, actualTitle);
        }else if(condition.equalsIgnoreCase("is not")){
            String expectedErrorMessage = "Invalid Login or Password.";
            String actualErrorMessage = webOrdersLogInPage.errorMessage.getText();
            Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
        }
    }


//    @Given("User with an existing order navigates to View all orders Tab")
//    public void user_with_an_existing_order_navigates_to_view_all_orders_tab() {
//        driver.get(CommonUtils.getProperty("WebOrdersURL"));
//
//    }
//
//    @When("User clicks on Edit icon next to his/her order")
//    public void user_clicks_on_edit_icon_next_to_his_her_order() {
//
//    }
//
//    @Then("User validates that his/her order can be edited")
//    public void user_validates_that_his_her_order_can_be_edited() {
//
//    }
//
//    @Given("User with an existing order navigates to View all orders")
//    public void user_with_an_existing_order_navigates_to_view_all_orders() {
//        driver.get(CommonUtils.getProperty("WebOrdersURL"));
//
//    }
//
//
//    @When("User clicks on Edit Icon next to his/her order")
//    public void user_clicks_on_edit_icon_next_to_his_her_order() {
//
//    }
//
//    @Then("User validates that his/her order can not be edited")
//    public void user_validates_that_his_her_order_can_not_be_edited() {
//
//    }
//
//    @Given("User  navigates to Edit Order Page")
//    public void user_navigates_to_edit_order_page() {
//        driver.get(CommonUtils.getProperty("WebOrdersURL"));
//
//    }
//
//    @When("User fills all mandatory fields and updates the order")
//    public void user_fills_all_mandatory_fields_and_updates_the_order() {
//
//    }
//    @Then("User is able to click on Update button and save changes")
//    public void user_is_able_to_click_on_update_button_and_save_changes() {
//
//    }
////
////
//    @Given("User  navigates to Edit Order Page")
//    public void user_navigates_to_edit_order_page() {
//        driver.get(CommonUtils.getProperty("WebOrdersURL"));
//
//    }
//
//    @When("User doesn't fill all mandatory fields")
//    public void user_doesn_t_fill_all_mandatory_fields() {
//
//    }
//
//    @Then("User is not able to click on Update button")
//    public void user_is_not_able_to_click_on_update_button() {
//
//    }


    @When("User clicks on Order Part")
    public void user_clicks_on_order_part() {
        webOrdersHomePage.orderPart.click();
    }

    @When("User adds new order with data")
    public void user_adds_new_order_with_data(DataTable dataTable) {
    List <Map<String,Object>> data = dataTable.asMaps(String.class, Object.class);
        editOrderPage.quantityBox.clear();
        editOrderPage.quantityBox.sendKeys(data.get(0).get("Quantity").toString());
        editOrderPage.customerNameBox.sendKeys(data.get(0).get("Customer name").toString());
        editOrderPage.streetBox.sendKeys(data.get(0).get("Street").toString());
        editOrderPage.cityBox.sendKeys(data.get(0).get("City").toString());
        editOrderPage.stateBox.sendKeys(data.get(0).get("State").toString());
        editOrderPage.zipBox.sendKeys(data.get(0).get("Zip").toString());
        editOrderPage.VisaCardButton.click();
        editOrderPage.cardNumBox.sendKeys(data.get(0).get("Card Num").toString());
        editOrderPage.expDateBox.sendKeys(data.get(0).get("Exp Date").toString());


    }

    @Then("User clicks on Process button and validates {string} message")
    public void user_clicks_on_process_button_and_validates_message(String success) {
        editOrderPage.processButton.click();
        String actualMessage = editOrderPage.verifiedOrderCreated.getText();
        Assert.assertEquals(success, actualMessage);

    }

    @When("User clicks View All Orders part")
    public void user_clicks_view_all_orders_part() {
     webOrdersHomePage.viewAllOrder.click();
    }

    @Then("User created order is added to list with data")
    public void user_created_order_is_added_to_list_with_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        Assert.assertEquals(data.get(0).get("Customer name"), webOrdersHomePage.firstRowData.get(1).getText());
        Assert.assertEquals(data.get(0).get("Quantity"), webOrdersHomePage.firstRowData.get(3).getText());
        Assert.assertEquals(data.get(0).get("Street"), webOrdersHomePage.firstRowData.get(5).getText());
        Assert.assertEquals(data.get(0).get("City"), webOrdersHomePage.firstRowData.get(6).getText());
        Assert.assertEquals(data.get(0).get("State"), webOrdersHomePage.firstRowData.get(7).getText());
        Assert.assertEquals(data.get(0).get("Zip"), webOrdersHomePage.firstRowData.get(8).getText());
        Assert.assertEquals(data.get(0).get("Card Num"), webOrdersHomePage.firstRowData.get(10).getText());
        Assert.assertEquals(data.get(0).get("Exp Date"), webOrdersHomePage.firstRowData.get(11).getText());


    }

    @Then("User validates UI headers with {string} excel file expected result")
    public void user_validates_ui_headers_with_excel_file_expected_result(String excelFile) {
        ExcelUtils.openExcelFile(excelFile,"Sheet1");
        String expectedResult = ExcelUtils.getValue(1,4);
        System.out.println(expectedResult);
        String [] results = expectedResult.split("1");


        Assert.assertEquals(results[1], editOrderPage.productLabel.getText());
        Assert.assertEquals(results[2], editOrderPage.productQuantityLabel.getText());
        Assert.assertEquals(results[3], editOrderPage.productPriceLabel.getText());
        Assert.assertEquals(results[4], editOrderPage.productDiscountLabel.getText());
        Assert.assertEquals(results[5], editOrderPage.productTotalLabel.getText());

    }

    @Then("User updates {string} with {string}")
    public void user_updates_with(String string, String string2) {
        ExcelUtils.setValue(1,6, string2);
    }

    @When("User creates all orders from {string} excel file")
    public void user_creates_all_orders_from_excel_file(String fileName) {
        int lastRow = ExcelUtils.openExcelFile(fileName, "Sheet1").getLastRowNum();

        List<List<String>> excelData = new ArrayList<>();

        for (int i = 1; i < lastRow; i++) {
            List<String> rowData = ExcelUtils.getRowValues(i);
            excelData.add(rowData);
        }

        for (int i = 0; i < excelData.size(); i++) {
            editOrderPage.quantityBox.clear();
            editOrderPage.quantityBox.sendKeys(excelData.get(i).get(0));
            editOrderPage.customerNameBox.sendKeys(excelData.get(i).get(1));
            editOrderPage.streetBox.sendKeys(excelData.get(i).get(2));
            editOrderPage.cityBox.sendKeys(excelData.get(i).get(3));
            editOrderPage.stateBox.sendKeys(excelData.get(i).get(4));
            editOrderPage.zipBox.sendKeys(excelData.get(i).get(5).substring(0,excelData.get(i).get(5).indexOf('.')));
            editOrderPage.VisaCardButton.click();
            editOrderPage.cardNumBox.sendKeys(excelData.get(i).get(6).substring(0,excelData.get(i).get(6).indexOf('.')));
            editOrderPage.expDateBox.sendKeys(excelData.get(i).get(7));
            editOrderPage.processButton.click();
        }


    }

    @Then("User validates that orders from {string} excel file are created")
    public void user_validates_that_orders_from_excel_file_are_created(String string) {

    }




}
