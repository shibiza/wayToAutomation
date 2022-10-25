package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveCartTest {

    public WebDriver webDriver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        this.webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        webDriver.manage().window().maximize();

        WebElement selectionCategory =
                webDriver.findElement(By.xpath("(//span[@class =\"a-size-small a-color-base truncate-2line\"])[1]"));
        selectionCategory.click();

        WebElement selectionItem =
                webDriver.findElement(By.xpath("(//*[@class = \"a-section aok-relative s-image-fixed-height\"])[1]"));
        selectionItem.click();

        WebElement additionToCart =
                webDriver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        additionToCart.click();
    }

    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void addToCart() {

        WebElement checkInformationLabelAddItem = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = \"a-size-medium-plus a-color-base sw-atc-text a-text-bold\"]")));
        Assert.assertEquals("Added to Cart", checkInformationLabelAddItem.getText());

        WebElement checkInformationLabelCart = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-cart-count\"]")));
        Assert.assertEquals("1", checkInformationLabelCart.getText());
    }

    @Test
    public void deleteFromCart() {

        WebElement openCart =
                webDriver.findElement(By.xpath("//*[@id = \"nav-cart\"]"));
        openCart.click();

        WebElement delCart =
                webDriver.findElement(By.cssSelector(".sc-action-delete .a-color-link"));
        delCart.click();

        WebElement checkInformationLabelDelItem = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = \"a-spacing-mini a-spacing-top-base\"]")));
        Assert.assertEquals("Your Amazon Cart is empty.", checkInformationLabelDelItem.getText());

        WebElement checkInformationLabelCart = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-cart-count\"]")));
        Assert.assertEquals("0", checkInformationLabelCart.getText());
    }
}

