import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class firstTest {

    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwiktrz3s8iEAxXJxzwCHUJ_BFEYABABGgJzZg&ase=2&gclid=Cj0KCQiA5-uuBhDzARIsAAa21T_lS4xrwXENTznJ7O7ikWOKV8tVQPCPtLc0imRpWYkB7egptcbP_N4aAkJpEALw_wcB&ohost=www.google.com&cid=CAESVeD2YHD-rjOpJSaO9R9ICg07C5-nM7mPdUcPSg2co_1Wd3DC6qQQuBHgwHGEMgLHqg2e8rEfYwNs8wcUMbqcXzFovepUAdUj1xjM7MGbspFcR0jyZTA&sig=AOD64_3trjX4TWLLERq_AaC7Fqq8F9E-Xg&q&nis=4&adurl&ved=2ahUKEwjs0LX3s8iEAxV-g2MGHR07Dy4Q0Qx6BAgEEAM");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[@id=\"accept-cookie-notification\"]")).click();

    }

    @Test
    public void login(){
        driver.findElement(By.xpath("//span[@class='item-text' and text()='Sign in']")).click();

    }

    @Test
    public void close(){

    }


}
