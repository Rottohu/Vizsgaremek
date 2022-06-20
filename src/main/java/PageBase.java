import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageBase {
    WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    private final String url = "https://lennertamas.github.io/portio/";
    private final By xButtonOnTerms = By.xpath("//*[@class=\"CloseIcon\"]");
    private final By registerTab = By.xpath("(//*[@id=\"register-form-button\"])[1]");
    private final By regUsername = By.xpath("//*[@id=\"register-username\"]");
    private final By regPassword = By.xpath("//*[@id=\"register-password\"]");
    private final By regButton = By.xpath("//*[@onclick=\"registerUser()\"]");
    private final By loginTab = By.xpath("(//*[@id=\"login-form-button\"])[2]");
    private final By loginUsername = By.xpath("//*[@id=\"email\"]");
    private final By loginPass = By.xpath("//*[@id=\"password\"]");
    private final By loginButton = By.xpath("//*[@onclick=\"myFunction()\"]");

    public void navigateToUrl()
    {
        driver.navigate().to(url);
    }

    public void popupKiller() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(xButtonOnTerms).click();
        Thread.sleep(1000);
    }

    public void registerAccount() throws InterruptedException {
        driver.findElement(registerTab).click();
        driver.findElement(regUsername).sendKeys("Testaccount");
        driver.findElement(regPassword).sendKeys("Testpassword");
        driver.findElement(regButton).click();
    }

    public void loginToAccount()
    {
        driver.findElement(loginTab).click();
        driver.findElement(loginUsername).sendKeys("Testaccount");
        driver.findElement(loginPass).sendKeys("Testpassword");
        driver.findElement(loginButton).click();
    }

    public void fullLogin() throws InterruptedException {
        navigateToUrl();
        popupKiller();
        registerAccount();
        loginToAccount();
    }
}
