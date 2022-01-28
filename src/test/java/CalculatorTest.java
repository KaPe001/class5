import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
class CalculatorTest {

    WebDriver driver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @DisplayName("I am testing bookingCom.pl")
    @ParameterizedTest
    @ValueSource(strings = {"Booking.com | Oficjalna strona | Najlepsze hotele i nie tylko"}) //goes to the method parameter
    @Order(1)
    @Tag("Regression")
    @Tag("Booking")
    void pageOneTest(String expectedTitle) {
        driver.get("https://www.booking.com");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        assertThat(actualTitle, equalTo(expectedTitle)); //dynamic
    }

    @ParameterizedTest
    @ValueSource(strings = {"Amazon.com. Spend less. Smile more."})
    @DisplayName("I am testing Amazon.com")
    @Order(2)
    @Tag("Regression")
    @Tag("Amazon")
    void pageTwoTest(String expectedTitle) {
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        assertThat(actualTitle, equalTo(expectedTitle));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pudelek.pl - Plotki, Gwiazdy, Sensacja - Pudelek", "kinga"})
    @DisplayName("I am testing pudelek.pl")
    @Order(3)
    void pageThreeTest(String expectedTitle) {
        driver.get("https://www.pudelek.pl");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        assertThat(actualTitle, equalTo(expectedTitle));
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}