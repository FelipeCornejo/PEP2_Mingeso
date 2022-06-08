import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {
    static WebDriver driver;

    @BeforeAll
    public static void initDriver() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

    }

    @Test
    @Order(1)
    // Registro Fallido por correo existente
    public void registro() {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).click();
        driver.findElement(By.id("email_create")).sendKeys("asdjkhfhkjhlsdfkjhsdafkhsdfhkjq@ca.cl");
        driver.findElement(By.cssSelector("#SubmitCreate > span")).click();

    }

    @Test
    @Order(2)
    // Iniciar sesión con credenciales inválidas
    public void failLogin(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("asdjkhfhkjhlsdfkjhsdafkhsdfhkjx@ca.cl");
        driver.findElement(By.id("passwd")).sendKeys("no_mi_contraseña");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.className("alert-danger"));
    }

    @Test
    @Order(3)
    // Registrarse con credenciales válidas
    public void successRegistrer(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).click();
        driver.findElement(By.id("email_create")).sendKeys("asdfasdfasdfsadfsdfx@gmail.com");
        driver.findElement(By.id("email_create")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Felipe");
        driver.findElement(By.id("customer_lastname")).sendKeys("Cornejo");
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).sendKeys("mingeso");
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("firstname")).sendKeys("Felipe");
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("lastname")).sendKeys("Cornejo");
        driver.findElement(By.id("company")).click();
        driver.findElement(By.id("company")).sendKeys("USACH");
        driver.findElement(By.id("address1")).click();
        driver.findElement(By.id("address1")).sendKeys("NO SE DONDE VIVO");
        driver.findElement(By.id("address2")).click();
        driver.findElement(By.id("address2")).sendKeys("SANTIAGO");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("CHILE");
        driver.findElement(By.id("id_state")).click();
        {
            Select id_state = new Select(driver.findElement(By.id("id_state")));
            id_state.selectByValue("1");
        }
        driver.findElement(By.id("postcode")).click();
        driver.findElement(By.id("postcode")).sendKeys("07003");
        driver.findElement(By.id("id_country")).click();
        {
            Select id_country = new Select(driver.findElement(By.id("id_country")));
            id_country.selectByValue("21");
        }
        driver.findElement(By.id("phone")).click();
        driver.findElement(By.id("phone")).sendKeys("123-123-1231");
        driver.findElement(By.id("phone_mobile")).click();
        driver.findElement(By.id("phone_mobile")).sendKeys("456-456-4564");
        driver.findElement(By.id("alias")).click();
        driver.findElement(By.id("alias")).sendKeys("a");
        driver.findElement(By.id("submitAccount")).click();
    }

    @Test
    @Order(4)
    // Iniciar sesión con credenciales válidas
    public void successLogin(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign out")).click();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("asdjkhfhkjhlsdfkjhsdafkhsdfhkj@ca.cl");
        driver.findElement(By.id("passwd")).sendKeys("admin");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }


}
