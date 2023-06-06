package com.browserstack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver webDriver;

    private String selectedProductName;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.selectedProductName = "";
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//*[@id=\"1\"]/p")
    private WebElement firstProductName;

    @FindBy(xpath = "//*[@id=\"1\"]/div[4]")
    private WebElement firstProductAddToCartButton;

    @FindBy(className = "float-cart__content")
    private List<WebElement> cartPane;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")
    private WebElement productCartText;

    public void selectFirstProductName() {
        String firstProduct = firstProductName.getText();
        setSelectedProductName(firstProduct);
    }

    public void clickAddToCartButton() {
        firstProductAddToCartButton.click();
    }

    public void waitForCartToOpen() {
        new WebDriverWait(webDriver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(cartPane));
    }

    public String getProductCartText() {
        return productCartText.getText();
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }
}
