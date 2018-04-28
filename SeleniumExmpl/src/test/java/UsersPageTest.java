import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UsersPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8000/users");
        assertEquals(driver.getTitle(), "SciNet");
    }

    @Test
    public void clickOnSearchButton() {
        WebElement searchField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("user-search")));
        searchField.clear();
        searchField.sendKeys("Abs");

        WebElement searchButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-secondary")));
        searchButton.click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert.alert-danger")));

        assertTrue(driver.getPageSource().contains("Нет результатов"));
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
