import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

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
    public void cadastroCompletoTest() {

        //Elemento nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Raoni");
        //Elemento sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Candido");
        //Elemento sexo
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        //Elemento comida favorita
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        //Select Escolaridade
        new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
        //Pratica esportes?
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Lorem ipsum dolor sit amet");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        //Asserções
        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertEquals("Nome: Raoni", driver.findElement(By.id("descNome")).getText());
        Assert.assertEquals("Sobrenome: Candido", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Carne Pizza", driver.findElement(By.id("/'descComida/'")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: O que eh esporte?", driver.findElement(By.id("descEsportes")).getText());
        Assert.assertEquals("Sugestoes: Lorem ipsum dolor sit amet", driver.findElement(By.id("descSugestoes")).getText());

        //Fim do teste
        driver.quit();
    }
}
