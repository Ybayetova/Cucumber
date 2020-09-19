package StepDefinition;

import Pages.EtsyHomePage;
import Utilities.CommonUtils;
import Utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class EtsySteps {

    WebDriver driver = Driver.getDriver("chrome");
    EtsyHomePage etsyHomePage = new EtsyHomePage();



    @Given("User navigates to Etsy application")
    public void user_navigates_to_etsy_application() {
       driver.get(CommonUtils.getProperty("EtsyURL"));
    }

    @When("User clicks on {string} part")
    public void user_clicks_on_part(String department) {

        if(department.equals("Jewelry & Accessories"))
            etsyHomePage.jewelryDept.click();
        else if(department.equals("Clothing & Shoes"))
            etsyHomePage.clothingDept.click();
        else if(department.equals("Home & Living"))
            etsyHomePage.homeDept.click();
        else if(department.equals("Wedding & Party"))
            etsyHomePage.weddingDept.click();
        else if(department.equals("Toys & Entertainment"))
             etsyHomePage.toysDept.click();
        else if(department.equals("Art & Collectibles"))
             etsyHomePage.artDept.click();
    }

    @Then("User validates {string} title")
    public void user_validates_title(String expectedTitle) {

        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

     @When("User searches for {string}")
     public void user_searches_for(String item) {
      etsyHomePage.searchBox.sendKeys(item + Keys.ENTER);
    }



    @Then("User validates the result contains")
    public void user_validates_the_result_contains(DataTable dataTable) {

        List<String> items = dataTable.asList();
        for(WebElement element: etsyHomePage.items){
            String itemName = element.getText();
            boolean isFound = false;
           for(String item: items){
               if(itemName.toLowerCase().contains(item)){
                  isFound= true;
               }
           }
           Assert.assertTrue(itemName,isFound);
        }


    }


    @When("User selects over ${int} option")
    public void user_selects_over_$_option(Integer int1) throws InterruptedException {
        try{
            etsyHomePage.filter.click();
        }catch (Exception ex){
            System.out.println("Filter button is not there");
        }
        etsyHomePage.over1000Button.click();
        Thread.sleep(5000);
    }


    @Then("User validates that all prices are over ${int}")
    public void user_validates_that_all_prices_are_over_$(Integer int1) {
      for(WebElement element: etsyHomePage.prices){
         String price = element.getText();  //1,010.50
          price = price.replace(",", "");
          double priceInDouble = Double.parseDouble(price);
          Assert.assertTrue(priceInDouble>int1);
      }
    }


}
