import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.sql.Driver;
import java.util.Set;

public class example {
    WebDriver driver;

    @Before
    public void setup(){
//        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
//        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void validate(){

        String title = driver.getTitle();
        System.out.println(title);
        String url = driver.getCurrentUrl();
        System.out.println(url);
        String source = driver.getPageSource();
        System.out.println(source);

        Assert.assertTrue(title.equals("Swag Labs"));

    }

    @Test
    public void validateLogin() throws InterruptedException {
        Point point = driver.manage().window().getPosition();
        System.out.println(point.x);
        System.out.println(point.y);

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        String fontColor = driver.findElement(By.id("user-name")).getCssValue("font-size");
        System.out.println(fontColor);
        Thread.sleep(3000);
        driver.findElement(By.id("password")).clear();
        String attr = driver.findElement(By.id("password")).getAttribute("placeholder");
        Assert.assertTrue(attr.equals("Password"));
        System.out.println(attr);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        String passwordValidate = driver.findElement(new By.ByXPath("//div[@class='login_password']/h4")).getText();
        System.out.println(passwordValidate);
        Thread.sleep(3000);
        Assert.assertTrue(passwordValidate.equals("Password for all users:"));
        if(driver.findElement(By.id("login-button")).isDisplayed()){
            driver.findElement(By.id("login-button")).click();
            System.out.println("Visible");

        }
        else{
            System.out.println("Invalid");
        }




        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", url);
    }

    @Test
    public void selectValidate() throws InterruptedException {
        validateLogin();
        WebElement element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(element);
//        select.selectByValue("lohi");
//        select.selectByIndex(1);
        select.selectByVisibleText("Price (high to low)");
        if(select.isMultiple()){
            System.out.println("Multiple dropdown");
        }
        else{
            System.out.println("Single dropdown");
        }
    }

    @Test

    public void multipleSelect(){
        driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");

        WebElement element = driver.findElement(By.xpath("//select[@name=\"FromLB\"]"));
        Select multipleSelect = new Select(element);
//        multipleSelect.selectByValue("USA");
        multipleSelect.selectByIndex(4);
        multipleSelect.selectByVisibleText("Greece");


        if(multipleSelect.isMultiple()){
            System.out.println("Multiple");

        }
        else{
            System.out.println("Single");
        }

        WebElement option1 = multipleSelect.getFirstSelectedOption();
        System.out.println(option1.getAttribute("value"));




    }

    @Test
    public void useXpath() throws InterruptedException {
        validateLogin();
//        driver.findElement(By.xpath("//input[contains(@id,'user')]"));
//        driver.findElement(By.xpath("//input[starts-with(@id,'user')]"));
//        driver.findElement(By.xpath("//input[@id='user-name' and @placeholder='Username']"));
//        driver.findElement(By.xpath("//input[@id='user-name' or @placeholder='Username']"));
//        driver.findElement(By.xpath("//div[text()=\"Sauce Labs Backpack\"]")).isDisplayed();
        driver.findElement(By.xpath("(//button[text()=\"Add to cart\"])[3]")).click();





    }

    @Test
    public void actionsMethods(){
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.id("user-name"));
        action.moveToElement(element).click().sendKeys("standard_user").doubleClick().contextClick().build().perform();

    }

    @Test
    public void alertHandling(){
       driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
       driver.findElement(By.xpath("//button[@onclick=\"myFunction()\"]")).click();
       driver.switchTo().alert().accept();
       driver.findElement(By.xpath("//button[@onclick=\"myFunctionf()\"]")).click();
       driver.switchTo().alert().sendKeys("pr76");

    }

    @Test
    public void cookiesHandling(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);

        Cookie user =  driver.manage().getCookieNamed("session-username");
        System.out.println(user);

        driver.manage().deleteCookie(user);
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("session-username");

        Cookie custom = new Cookie("password", "pass");
        driver.manage().addCookie(custom);

        System.out.println(driver.manage().getCookieNamed("password"));

    }

    @Test
    public void tableHandling(){
        driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
        driver.findElement(By.xpath("//table[@cellpadding=\"10\"]//tr[3]//td[6]//input[@id=\"submitButton\"]")).click();



    }

    @Test
    public void iframehandling(){
        driver.navigate().to("https://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame("mce_0_ifr");
        driver.findElement(By.xpath("//body[@id=\"tinymce\"]/p")).clear();
        driver.findElement(By.xpath("//body[@id=\"tinymce\"]/p")).sendKeys("Praveen");

    }

    @Test
    public void javaScriptExecutor(){
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",btnLogin);

        js.executeScript("window.scrollBy(0,142)");
    }

//    @After
////    public void close(){
////        driver.close();
////    }

}
