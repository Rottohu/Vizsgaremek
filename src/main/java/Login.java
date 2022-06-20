import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends PageBase {

    public Login(WebDriver driver) {
        super(driver);
    }

    private final By loginTab = By.xpath("(//*[@id=\"login-form-button\"])[2]");
    private final By loginUsername = By.xpath("//*[@id=\"email\"]");
    private final By loginPass = By.xpath("//*[@id=\"password\"]");
    private final By loginButton = By.xpath("//*[@onclick=\"myFunction()\"]");

    private final String loggedInUrl = "https://lennertamas.github.io/portio/landing.html";

    public String getLoggedInUrl() {
        return loggedInUrl;
    }

    public void clickLoginTab() {
        driver.findElement(loginTab).click();
    }

    public void sendUsername(String username) {
        driver.findElement(loginUsername).sendKeys(username);
    }

    public void sendPassword(String password) {
        driver.findElement(loginPass).sendKeys(password);
    }

    public void pushLoginButton() {
        driver.findElement(loginButton).click();
    }
}
