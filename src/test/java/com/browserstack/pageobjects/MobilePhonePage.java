package com.browserstack.pageobjects;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

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
    WebDriverWait webDriverWait;

    final String searchfieldLocator = "input[id='searchField_%d']";
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
        By by = By.cssSelector(String.format(searchfieldLocator, searchFieldNumber));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement searchField = driver.findElement(by);
        searchField.sendKeys(mobileName);
    }

    public void selectMobile(String mobileName) {
        By by = By.xpath(String.format(mobileLocator, mobileName));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement mobileToCompare = driver.findElement(by);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileToCompare));
        mobileToCompare.click();
    }

    public void printTheSearchResultsAsPerPrinceInAscendingOrder() {
        mobilePhoneList.parallelStream().sorted(Comparator.comparing( element ->
                element.findElement(By.className("compare-mob-price")).getText()
        ));
        mobilePhoneList.stream().forEach(element -> {System.out.println(element.findElement(By.cssSelector(".mobile-details a")).getText());});
    }

    public void printTheSearchResultsAsPerExpertScoreInDescendingOrder() {
        mobileRatingList.parallelStream().sorted(Comparator.comparing(element -> element.getText()).reversed());
    }
}
