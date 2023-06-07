package com.browserstack.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MobilePhonePage {

    WebDriver driver;

    @FindBy(css = "a[data-label='Mobiles']")
    WebElement mobileLink;

    @FindBy(css = "a[data-label='Mobiles::Compare Mobiles']")
    WebElement compareMobileLink;

    @FindBy(css = "div.mobile_active")
    List<WebElement> mobilePhoneList;

    @FindBy(css = "span.prdct-dtl__tlbr-item__scre")
    List<WebElement> mobileRatingList;

    private static final Logger LOGGER = LogManager.getLogger(MobilePhonePage.class);

    @FindBy(css = ".prdt_name")
    List<WebElement> productNameList;
    WebDriverWait webDriverWait;

    final String searchfieldLocator = "input[id='searchField_%d']";
    final String searchfieldTopLocator = "input[id='searchField_%d_top']";

    final String mobileLocator = "//*[text()='%s']";

    public MobilePhonePage(WebDriver webDriver){
        driver = webDriver;
        PageFactory.initElements(webDriver,this);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverMobilesLink(){
        webDriverWait.until(ExpectedConditions.visibilityOf(mobileLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(mobileLink).perform();
    }

    public void clickOnCompareMobiles(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(compareMobileLink));
        compareMobileLink.click();
    }
    public void enterMobileNameInSearchField(String mobileName, int searchFieldNumber) {
        String locator = String.format( (searchFieldNumber>0? searchfieldTopLocator :searchfieldLocator), searchFieldNumber);
        By by = By.cssSelector(locator);
        WebElement searchField = driver.findElement(by);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(mobileName);
        by = By.xpath(String.format(mobileLocator, mobileName));
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException timeoutException){
            searchField.sendKeys(mobileName);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }

    public void selectMobile(String mobileName) {
        By by = By.xpath(String.format(mobileLocator, mobileName));
        WebElement mobileToCompare = driver.findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        try {
            executor.executeScript("arguments[0].click();", mobileToCompare);
        } catch (StaleElementReferenceException se){

            mobileToCompare = driver.findElement(by);
            executor.executeScript("arguments[0].click();", mobileToCompare);
        }
    }

    public void printTheSearchResultsAsPerPrinceInAscendingOrder() {

        Map<Integer,String> phonePriceMap = new TreeMap<>();
        mobilePhoneList.parallelStream().forEach(element -> {
            int price = Integer.parseInt(element.findElement(By.cssSelector(".compare-mob-price>span")).getText().replace("₹", "").replace(",","").replaceAll(" ",""));
            String name = element.findElement(By.className("mobile-details")).getText();
            phonePriceMap.put(price,name);
        });
        phonePriceMap.entrySet().stream().forEach(entry -> LOGGER.info(String.format("<%s - %s>",entry.getValue(),entry.getKey())));
    }

    public void printTheSearchResultsAsPerExpertScoreInDescendingOrder() {
        Map<Float,String> phoneRateMap = new TreeMap<>(Collections.reverseOrder());
        final AtomicInteger index = new AtomicInteger(0);
        mobileRatingList.forEach(element -> {
            float rating = Float.parseFloat(element.getText());
            String name = mobilePhoneList.get(index.getAndIncrement()).findElement(By.className("mobile-details")).getText();
            phoneRateMap.put(rating,name);
        });
        phoneRateMap.entrySet().stream().forEach(entry -> LOGGER.info(String.format("<%s - %s>",entry.getValue(),entry.getKey())));
    }
}
