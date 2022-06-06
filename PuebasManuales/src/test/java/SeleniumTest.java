import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    @Order(2)
    // Registro con un correo que ya está registrado
    public void registro() {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).click();
        driver.findElement(By.id("email_create")).sendKeys("felipe.cornejo.i@usach.cl");
        driver.findElement(By.cssSelector("#SubmitCreate > span")).click();

    }


    @Test
    @Order(7)
    // Filtrar los productos por alguna categoría
    public void filterByCategory() {
        driver.findElement(By.linkText("WOMEN")).click();
    }


    @Test
    @Order(11)
    //Buscar una prenda con un nombre en específico, existente
    public void findProduct(){
        driver.findElement(By.id("search_query_top")).sendKeys("Printed Dress");
        driver.findElement(By.name("submit_search")).click();
        driver.findElement(By.linkText("Printed Dress")).click();
    }


    @Test
    @Order(6)
    //Agregar una prenda a la lista de deseos
    public void addProductoToWishList() {
        driver.findElement(By.id("search_query_top")).sendKeys("Blouse");
        driver.findElement(By.name("submit_search")).click();
        driver.findElement(By.linkText("Blouse")).click();
        driver.findElement(By.id("wishlist_button")).click();
        WebElement element = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Close']")));
        element.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.findElement(By.linkText("My account")).click();
        driver.findElement(By.linkText("MY WISHLISTS")).click();
        driver.findElement(By.className("lnk_wishlist")).click();
        driver.findElement(By.linkText("Blouse"));
    }

    @Test
    @Order(3)
    // Iniciar sesión con credenciales inválidas
    public void failLogin(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("not_my_mail@usach.cl");
        driver.findElement(By.id("passwd")).sendKeys("nanananananana");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.className("alert-danger"));
    }


    @Test
    @Order(5)
    //Ingresar a perfil de usuario
    public void enterProfile(){
        if (driver.findElement(By.className("account")).isEnabled()) {
            driver.findElement(By.className("account")).click();
        }
    }

    @Test
    @Order(4)
    // Iniciar sesión con credenciales válidas
    public void successLogin(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("littlecornejo@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("mingeso");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Test
    @Order(12)
    //Agregar un producto al carrito de compras
    public void addToCart(){
        //driver.findElement(By.linkText("Printed Dress")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 50)");
        driver.findElement(By.id("add_to_cart")).click();
        driver.findElement(By.className("icon-ok"));
    }


    @Test
    @Order(8)
    //Suscribirse a las noticias de la página
    public void subscribeToNewsletter(){
        driver.findElement(By.id("newsletter-input")).sendKeys("asdfasdfasdfsadfsdf@gmail.com");
        driver.findElement(By.name("submitNewsletter")).click();
        driver.findElement(By.className("alert-success"));
        driver.manage().window().setSize(new Dimension(1536, 835));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Test
    @Order(10)
    // Registrarse con credenciales válidas
    public void successRegistrer(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).click();
        driver.findElement(By.id("email_create")).sendKeys("asdfasdfasdfsadfsdf@gmail.com");
        driver.findElement(By.id("email_create")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Felipe");
        driver.findElement(By.id("customer_lastname")).sendKeys("Cornejo");
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).sendKeys("mingeso");
        driver.findElement(By.id("days")).click();
        {
            Select days = new Select(driver.findElement(By.id("days")));
            days.selectByValue("7");
        }
        driver.findElement(By.id("months")).click();
        {
            Select months = new Select(driver.findElement(By.id("months")));
            months.selectByValue("3");
        }
        driver.findElement(By.id("years")).click();
        {
            Select years = new Select(driver.findElement(By.id("years")));
            years.selectByValue("2000");
        }
        driver.findElement(By.id("firstname")).click();
        driver.findElement(By.id("firstname")).sendKeys("Felipe");
        driver.findElement(By.id("lastname")).click();
        driver.findElement(By.id("lastname")).sendKeys("Cornejo");
        driver.findElement(By.id("company")).click();
        driver.findElement(By.id("company")).sendKeys("USACH");
        driver.findElement(By.id("address1")).click();
        driver.findElement(By.id("address1")).sendKeys("NO SE DONDE VIVO 1313");
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
        driver.findElement(By.id("alias")).sendKeys("YOYO");
        driver.findElement(By.id("submitAccount")).click();
    }

    @Test
    @Order(1)
    // Enviar un reclamo o sugerencia
    public void sendClaim(){
        driver.findElement(By.linkText("Contact us")).click();
        driver.findElement(By.id("id_contact")).click();
        {
            Select opcion = new Select(driver.findElement(By.id("id_contact")));
            opcion.selectByValue("2");
        }
        driver.findElement(By.id("email")).sendKeys("littlecornejo@gmail.com");
        driver.findElement(By.id("id_order")).sendKeys("mingeso");
        driver.findElement(By.id("message")).sendKeys("Pueden por favor traer unos polerones de panda");
        driver.findElement(By.id("submitMessage")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Order(9)
    public void logOut(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        driver.findElement(By.linkText("Sign out")).click();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }


}
