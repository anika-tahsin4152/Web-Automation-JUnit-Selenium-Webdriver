import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJUnitTest {
    public static WebDriver driver;


    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void scroll(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }

    @Test
    public void WebForm() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        while (true) {
            driver.navigate().refresh();
            driver.findElements(By.className("onetrust-close-btn-handler")).get(0).click();

            driver.findElement(By.id("edit-name")).sendKeys("Anika Tahsin Hridi");
            driver.findElement(By.id("edit-number")).sendKeys("+8801990215891");

//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            List<WebElement> buttons = driver.findElements(By.className("ui-checkboxradio-label"));
//            buttons.get(0).click();

            Actions action = new Actions(driver);
            List<WebElement> buttons = driver.findElements(By.className("ui-checkboxradio-label"));
            action.click(buttons.get(0)).perform();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            WebElement dateInput = driver.findElement(By.id("edit-date"));
            dateInput.click();
            dateInput.clear();


            dateInput.sendKeys("04");
            dateInput.sendKeys(Keys.ARROW_RIGHT);
            dateInput.sendKeys("o");
            dateInput.sendKeys(Keys.ARROW_RIGHT);
            dateInput.sendKeys("2023");


            scroll(0, 1000);
            driver.findElement(By.id("edit-email")).sendKeys("hriditahsin5891@email.com");
            driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Dear Hiring Manager,\n" +
                    "\n" +
                    "I am applying for the SQA Engineer position as a recent graduate from North-South University. I am passionate about software quality assurance and eager to contribute my skills and dedication to your team. My education has equipped me with a strong foundation in testing methodologies, and I am excited about the opportunity to ensure the highest standards of software quality at your company.\n" +
                    "\n" +
                    "Sincerely,\n" +
                    "Anika Tahsin Hridi");


            WebElement fileInput = driver.findElement(By.id("edit-uploadocument-upload"));
            String filePath = System.getProperty("user.dir") + "/src/test/resources/panda.jpg";
            fileInput.sendKeys(filePath);

            WebElement checkbox = driver.findElement(By.className("form-check-input"));
            checkbox.sendKeys(Keys.SPACE);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement submitButton = (WebElement) By.className("webform-button--submit");
            submitButton.click();


            String confirmationMessage = "Thank you for your submission!";

            // For example, you can assert that the confirmation message is displayed
            WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            String actualConfirmationText = confirmationElement.getText();
            String expectedConfirmationText = "Thank you for your submission!";
            assert actualConfirmationText.contains(expectedConfirmationText) :
                    "Confirmation message not found or does not match the expected message.";
        }
    }

    @AfterAll
    public static void quitBrowser() {

        driver.quit();

    }
}

