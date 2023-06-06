package com.browserstack.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class WebdriverManager {
    
    private WebDriver driver;
    public WebDriver getDriver(){
        if(driver==null){
            driver = createDriver();
        }
        return driver;
    }

    @SneakyThrows
    private WebDriver createDriver() {
        String platform = System.getProperty("platform");

        switch (platform.toUpperCase()){
            case "BROWSERSTACK":
                return browserStackDriver();
            case "CHROME":
                return chromeDriver();
            case "FIREFOX":
                return fireFoxDriver();
            case "EDGE":
                return edgeDriver();
        }
        return null;
    }

    private WebDriver edgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(options);
    }

    private WebDriver fireFoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origins=*");
        return new FirefoxDriver(options);
    }

    private WebDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    @SneakyThrows
    private WebDriver browserStackDriver() {
            MutableCapabilities capabilities = new MutableCapabilities();
            HashMap<String, String> bstackOptions = new HashMap<>();
            bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
            capabilities.setCapability("bstack:options", bstackOptions);
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }
}
