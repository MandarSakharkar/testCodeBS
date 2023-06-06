package com.browserstack.stepdefs;

import com.browserstack.pageobjects.MobilePhonePage;
import com.browserstack.utils.WebdriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CompareMobileSteps {
    WebDriver driver;
    MobilePhonePage mobilePhonePage;

    @Before
    public void setUp(){
        driver = new WebdriverManager().getDriver();
        mobilePhonePage = new MobilePhonePage(driver);
    }

    @Given("^I am on the webPage '(.+)'$")
    public void I_am_on_the_website(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @When("I navigate to compare mobiles page")
    public void iNavigateToCompareMobilesPage() {
        mobilePhonePage.hoverMobilesLink();
        mobilePhonePage.clickOnCompareMobiles();
    }

    @When("I entered mobile name as {string} in search field {int}")
    public void iEnteredMobileNameAsSamsungGalaxySInSearchField(String mobileName, int searchFieldNumber) {
        mobilePhonePage.enterMobileNameInSearchField(mobileName,searchFieldNumber);
    }

    @SneakyThrows
    @When("I select mobile as {string}")
    public void iSelectMobileAsSamsungGalaxyS(String mobileName) {
        mobilePhonePage.selectMobile(mobileName);
        Thread.sleep(1000);
    }

    @SneakyThrows
    @When("I print the search results as per price in ascending order")
    public void iPrintTheSearchResultsAsPerPrinceInAscendingOrder() {
        Thread.sleep(1000);
        mobilePhonePage.printTheSearchResultsAsPerPrinceInAscendingOrder();
    }

    @When("I print the search results as per expert score in descending order")
    public void iPrintTheSearchResultsAsPerExpertScoreInDescendingOrder() {
        mobilePhonePage.printTheSearchResultsAsPerExpertScoreInDescendingOrder();
    }
}
