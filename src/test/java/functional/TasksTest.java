package functional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TasksTest {

    private WebDriver webDriver;

    @Before
    public void initialize(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\carla\\Desktop\\devops\\chromedriver.exe");
        webDriver =  new ChromeDriver();
        webDriver.navigate().to("http://localhost:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void deveSalvarTaskComSucesso() {
        try{
            //encontrar o botão AddTodo
            webDriver.findElement(By.id("addTodo")).click();

            //escrever descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2051");

            //salvar
            webDriver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = webDriver.findElement(By.id("message")).getText();
            assertEquals("Success!", mensagem);
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void deveGerarMensagemErroComDataPassada() {
        try {
            //encontrar o botão AddTodo
            webDriver.findElement(By.id("addTodo")).click();

            //escrever descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2011");

            //salvar
            webDriver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = webDriver.findElement(By.id("message")).getText();
            assertEquals("Due date must not be in past", mensagem);
        } finally {
            webDriver.quit();
        }


    }

    @Test
    public void deveGerarMensagemErroQuandoNaoPassarData() {
        try {
            //encontrar o botão AddTodo
            webDriver.findElement(By.id("addTodo")).click();

            //escrever descrição
            webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            //webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2011");

            //salvar
            webDriver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = webDriver.findElement(By.id("message")).getText();
            assertEquals("Fill the due date", mensagem);
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void deveGerarMensagemErroQuandoNãoPassarDescricao() {
        try {
            //encontrar o botão AddTodo
            webDriver.findElement(By.id("addTodo")).click();

            //escrever descrição
            //webDriver.findElement(By.id("task")).sendKeys("Teste via Selenium");
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2011");

            //salvar
            webDriver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = webDriver.findElement(By.id("message")).getText();
            assertEquals("Fill the task description", mensagem);
        } finally {
            webDriver.quit();
        }
    }

}
