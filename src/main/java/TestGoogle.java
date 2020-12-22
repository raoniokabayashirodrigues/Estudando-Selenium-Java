import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class TestGoogle {

    @Test
    public void test() {

        System.setProperty("webdriver.edge.driver","C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Raoni Candido Rodrigues");
        Assert.assertEquals("Raoni Candido Rodrigues", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComTextArea(){
        System.setProperty("webdriver.edge.driver","C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de Texto longo");

        Assert.assertEquals("Teste de Texto longo", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton(){

        System.setProperty("webdriver.edge.driver","C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }

    @Test
    public void deveInteragirComComboBox(){

        System.setProperty("webdriver.edge.driver","C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
//        combo.selectByIndex(3);
//        combo.selectByValue("superior");
        combo.selectByVisibleText("2o grau completo");
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());

        driver.quit();
    }

    @Test
    public void deveVerificarValoresCombo() {

        System.setProperty("webdriver.edge.driver", "C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

        combo.selectByVisibleText("2o grau completo");
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option: options){
            if (option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);

        driver.quit();

    }

    @Test
    public void deveVerificarValoresComboMultiplo() {

        System.setProperty("webdriver.edge.driver", "C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

        driver.quit();
    }

    @Test
    public void deveClicarNoBotaoClickMe() {

        System.setProperty("webdriver.edge.driver", "C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));

        driver.quit();

    }

    @Test
    @Ignore
    public void deveInteragirComLinks() {

        System.setProperty("webdriver.edge.driver", "C:\\cmder\\bin\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        driver.findElement(By.linkText("Voltar")).click();
        WebElement voltou = driver.findElement(By.id("resultado"));
        Assert.assertEquals("Voltou!", voltou.getText());
    }


}

