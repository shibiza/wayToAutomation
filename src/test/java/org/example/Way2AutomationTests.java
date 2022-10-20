package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Way2AutomationTests {

    public WebDriver webDriver;
    public String writeFirstName = "Natallia";
    public String writeLastName = "Samoilik";
    public String writePhoneNumber = "572111551";
    public String writeUserName = "Natali";
    public String writeEmail = "samoiliknata@gmail.com";
    public String wrongEmail = "samoiliknatagmail.com";
    public String writePassword = "123456";
    public String warningBadEmail = "Please enter a valid email address.";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        this.webDriver = new ChromeDriver();
        webDriver.get("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void finish() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void writeCredentials() {

        //      fulfill First Name and Last Name forms
        WebElement firstNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[1]/p[1]/input"));
        firstNameForm.click();
        firstNameForm.sendKeys(writeFirstName);

        WebElement lastNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[1]/p[2]/input"));
        lastNameForm.click();
        lastNameForm.sendKeys(writeLastName);

        //      select marital status and hobby:
        WebElement maritalStatusMarried = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[2]/div/label[2]/input"));
        maritalStatusMarried.click();

        WebElement hobbyReading = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[3]/div/label[2]/input"));
        hobbyReading.click();

        //      fulfill phone number, Username
        WebElement phoneNumberForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[6]/input"));
        phoneNumberForm.click();
        phoneNumberForm.sendKeys(writePhoneNumber);

        WebElement userNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[7]/input"));
        userNameForm.click();
        userNameForm.sendKeys(writeUserName);

        //   fullfill WRITE email
        WebElement emailForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[8]/input"));
        emailForm.click();
        emailForm.sendKeys(writeEmail);

        //   entering password
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[11]/input"));
        password.click();
        password.sendKeys(writePassword);

        //      pressing SUBMIT button
        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[13]/input"));
        submitButton.click();
    }

    @Test
    public void wrongCredentials() {

        //      fulfill First Name and Last Name forms
        WebElement firstNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[1]/p[1]/input"));
        firstNameForm.click();
        firstNameForm.sendKeys(writeFirstName);

        WebElement lastNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[1]/p[2]/input"));
        lastNameForm.click();
        lastNameForm.sendKeys(writeLastName);

        //      select marital status and hobby:
        WebElement maritalStatusMarried = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[2]/div/label[2]/input"));
        maritalStatusMarried.click();

        WebElement hobbyReading = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[3]/div/label[2]/input"));
        hobbyReading.click();

        //      fulfill phone number, Username
        WebElement phoneNumberForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[6]/input"));
        phoneNumberForm.click();
        phoneNumberForm.sendKeys(writePhoneNumber);

        WebElement userNameForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[7]/input"));
        userNameForm.click();
        userNameForm.sendKeys(writeUserName);

        //   fullfill WRONG email
        WebElement emailForm = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[8]/input"));
        emailForm.click();
        emailForm.sendKeys(wrongEmail);

        //   entering password
        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[11]/input"));
        password.click();
        password.sendKeys(writePassword);

        //      pressing SUBMIT button
        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[13]/input"));
        submitButton.click();

        //      expecting a warning "Please enter a valid email address." and compare
        WebElement warningWrongEmail = webDriver.findElement(By.xpath("//*[@id=\"register_form\"]/fieldset[8]/label[2]"));

        Assert.assertTrue(warningWrongEmail.getText().contains(warningBadEmail));
    }
}
