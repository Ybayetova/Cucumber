package StepDefinition;

import Pages.MyAppPage;
import Utilities.Driver;
import Utilities.JDBCUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAppSteps {

    MyAppPage myAppPage = new MyAppPage();
    WebDriver driver = Driver.getDriver("browser");
    List<Map<String, Object>> uiData = new ArrayList<>();


    @Given("User navigates to MyApp homepage")
    public void user_navigates_to_MyApp_homepage() {
          driver.get("file:///Users/yerkenazbayetova/Downloads/TEch.html");
    }


    @When("User gets all data from UI")
    public void user_gets_all_data_from_UI() {

        Map<String, Object> map1 = new HashMap<>();
        for(int i=0; i<myAppPage.row1Data.size(); i++){
           map1.put(myAppPage.columnNames.get(i).getText(), myAppPage.row1Data.get(i).getText());
        }
        uiData.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        for(int i=0; i<myAppPage.row2Data.size(); i++){
            map2.put(myAppPage.columnNames.get(i).getText(), myAppPage.row2Data.get(i).getText());
        }
        uiData.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        for(int i=0; i<myAppPage.row3Data.size(); i++){
            map3.put(myAppPage.columnNames.get(i).getText(),myAppPage.row3Data.get(i).getText());
        }
        uiData.add(map3);


        Map<String, Object> map4 = new HashMap<>();
        for(int i=0; i<myAppPage.row4Data.size(); i++){
            map4.put(myAppPage.columnNames.get(i).getText(),myAppPage.row4Data.get(i).getText());
        }
        uiData.add(map4);

    }

    @Then("User validates UI data with DB data")
    public void user_validates_UI_data_with_DB_data() throws SQLException {
        JDBCUtils.establishConnection();
        List<Map<String,Object>> dbData = JDBCUtils.runQuery("select first_name, last_name, employee_id, job_title\n" +
                "from employees e join jobs\n" +
                "using(job_id)\n" +
                "where employee_id in (100,105,119,125) ");

        for(int i=0; i<uiData.size(); i++){
            Assert.assertEquals(dbData.get(i).get("FIRST_NAME").toString(), uiData.get(i).get("Name").toString());
            Assert.assertEquals(dbData.get(i).get("LAST_NAME").toString(), uiData.get(i).get("Last Name").toString());
            Assert.assertEquals(dbData.get(i).get("EMPLOYEE_ID").toString(), uiData.get(i).get("Employee ID").toString());
            Assert.assertEquals(dbData.get(i).get("JOB_TITLE").toString(), uiData.get(i).get("JOB_TITLE").toString());

        }

    }


}
