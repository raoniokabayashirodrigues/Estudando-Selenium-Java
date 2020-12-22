import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TesteCampoTreinamento {

    @Test
    public void teste(){

        System.setProperty("webdriver.edge.driver","C:\\cmder\\bin\\msedgedriver.exe");

//      Microsoft Edge Driver
        WebDriver driver = new EdgeDriver();
        /*Redimensionar navegador*/
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://www.google.com/");
        Assert.assertEquals( "Google", driver.getTitle());
    }
}
