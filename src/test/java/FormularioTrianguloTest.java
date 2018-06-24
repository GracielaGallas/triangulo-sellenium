import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Screenshot;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FormularioTrianguloTest {

    private static final String DEZ = "10";
    private static final String VINTE = "20";
    private static final String TRINTA = "30";
    private static final String ZERO = "0";

    private static final String PATH_CHROMEDRIVER = ".\\webdriver\\chromedriver.exe";
    private static final String PATH_EVIDENCIAS = ".\\evidencias\\";

    private WebDriver navegador;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", PATH_CHROMEDRIVER);
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.get("localhost:9090/#/");
        navegador.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void devePreencherFormularioEValidarTriangulo() {
        //Caso de teste 01
        navegador.findElement(By.name("sideA")).sendKeys(DEZ);
        navegador.findElement(By.name("sideB")).sendKeys(DEZ);
        navegador.findElement(By.name("sideC")).sendKeys(DEZ);
        navegador.findElement(By.xpath("//button[@class=\"btn btn-success\"]")).click();
        WebElement resposta1 = navegador.findElement(By.xpath("//*[contains(text(), 'Equilátero')]"));
        String respostaAtual1 = resposta1.getText();
        assertEquals("Equilátero", respostaAtual1);
        Screenshot.take(navegador, PATH_EVIDENCIAS + "caso_teste_01.png");
        navegador.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();

        //Caso de teste 02
        navegador.findElement(By.name("sideA")).sendKeys(DEZ);
        navegador.findElement(By.name("sideB")).sendKeys(DEZ);
        navegador.findElement(By.name("sideC")).sendKeys(VINTE);
        navegador.findElement(By.xpath("//button[@class=\"btn btn-success\"]")).click();
        WebElement resposta2 = navegador.findElement(By.xpath("//*[contains(text(), 'Isóceles')]"));
        String respostaAtual2 = resposta2.getText();
        assertEquals("Isóceles", respostaAtual2);
        Screenshot.take(navegador, PATH_EVIDENCIAS + "caso_teste_02.png");
        navegador.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();

        //Caso de teste 03
        navegador.findElement(By.name("sideA")).sendKeys(DEZ);
        navegador.findElement(By.name("sideB")).sendKeys(VINTE);
        navegador.findElement(By.name("sideC")).sendKeys(TRINTA);
        navegador.findElement(By.xpath("//button[@class=\"btn btn-success\"]")).click();
        WebElement resposta3 = navegador.findElement(By.xpath("//*[contains(text(), 'Escaleno')]"));
        String respostaAtual3 = resposta3.getText();
        assertEquals("Escaleno", respostaAtual3);
        Screenshot.take(navegador, PATH_EVIDENCIAS + "caso_teste_03.png");
        navegador.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();
        navegador.close();
    }

    @Test
    public void deveFalharSeAlgumParametroForZero() {
        //Caso de teste 04
        navegador.findElement(By.name("sideA")).sendKeys(ZERO);
        navegador.findElement(By.name("sideB")).sendKeys(VINTE);
        navegador.findElement(By.name("sideC")).sendKeys(TRINTA);
        navegador.findElement(By.xpath("//button[@class=\"btn btn-success\"]")).click();
        WebElement resposta4 = navegador.findElement(By.xpath("//div[@class='alert alert-danger fade show']"));
        String respostaAtual4 = resposta4.getText();
        assertEquals("Não é um triângulo", respostaAtual4);
        Screenshot.take(navegador, PATH_EVIDENCIAS + gerarTimestamp() + "caso_teste_04.png");
        navegador.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();
        navegador.close();
    }

    private static String gerarTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }

}
