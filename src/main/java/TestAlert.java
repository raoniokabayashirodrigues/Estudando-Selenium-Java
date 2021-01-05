import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class TestAlert {

    private WebDriver driver;

    @Before
    public void inicializa(){
        //Executing with Google Chrome
        System.setProperty("webdriver.chromedriver","C:\\cmder\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");

        //OR EXECUTE TO FIREFOX
        /*
         System.setProperty("webdriver.geckodriver", "C:\\cmder\\bin\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        */
    }
    @After
    public void finalizar(){
        driver.quit();
    }


    @Test
    public void deveInteragirComAlert() {
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Alert Simples", alert.getText());
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertConfirm() {

        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();


        driver.findElement(By.id("confirm")).click();
        driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado", alert.getText());
        alert.dismiss();

    }

    @Test
    public void deveInteragirComAlertPrompt() {

        driver.findElement(By.id("prompt")).click();
        Alert alerta = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alerta.getText());
        alerta.sendKeys("12");
        alerta.accept();
        Assert.assertEquals("Era 12?", alerta.getText());
        alerta.accept();
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();

    }
}
