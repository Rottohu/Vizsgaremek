import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Privacy extends PageBase {
    public Privacy(WebDriver driver) {
        super(driver);
    }

    private final By xButtonOnTerms = By.xpath("//*[@class=\"CloseIcon\"]");
    private final By displayStyle = By.xpath("//*[@class=\"overlay\"]");
    private final By acceptButton = By.xpath("//*[@id=\"terms-and-conditions-button\"]");
    private final By nowhere = By.xpath("//*[@class=\"container\"]");

    public void clickOnX() {
        driver.findElement(xButtonOnTerms).click();
    }

    public String getDisplayStyle() {
        return driver.findElement(displayStyle).getCssValue("display");
    }

    public void clickOnAccept() {
        driver.findElement(acceptButton).click();
    }

    public void clickOnNowhere() {
        driver.findElement(nowhere).click();
    }

}
