import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOut extends PageBase{

    public LogOut(WebDriver driver) {
        super(driver);
    }

    private final By logOutButton = By.xpath("//*[@onclick='logout()']");
    private final By visitorSide = By.xpath("//body[@onload='openPage()']");

    public void clickOnLogout()
    {
        driver.findElement(logOutButton).click();
    }

    public Boolean isLoggedOut()
    {
        return driver.findElement(visitorSide).isDisplayed();
    }
}
