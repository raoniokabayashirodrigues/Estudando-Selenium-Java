import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    private WebDriver driver;

    @Before
    public void inicializa(){
        System.setProperty("webdriver.chromedriver","C:\\cmder\\bin\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");
    }
    @After
    public void finalizar(){
        driver.quit();
    }


    @Test
    public void testPrimeiroNome() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Raoni");
        Assert.assertEquals("Raoni", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComTextArea(){
        driver.findElement(By.tagName("textarea")).sendKeys("Teste de Texto longo");
        Assert.assertEquals("Teste de Texto longo", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComRadioButton(){
        driver.get("file:\\" +  System.getProperty("user.dir") +"\\src\\main\\resources\\componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComComboBox(){
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
//        combo.selectByIndex(3);
//        combo.selectByValue("superior");
        combo.selectByVisibleText("2o grau completo");
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void deveVerificarValoresCombo() {

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
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {

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
    }

    @Test
    public void deveClicarNoBotaoClickMe() {
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
    }

    @Test
    public void deveInteragirComLinks() {

        driver.findElement(By.linkText("Voltar")).click();
        WebElement voltou = driver.findElement(By.id("resultado"));
        Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
    }

    @Test
    public void deveBuscarTextoNaPagina() {
//        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
    }
}
