import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModifyData extends PageBase {
    public ModifyData(WebDriver driver) {
        super(driver);
    }

    private By profileButton = By.id("profile-btn");
    private By nameInput = By.id("name");
    private By bioInput = By.id("bio");
    private By phoneInput = By.id("phone-number");
    private By saveButton = By.xpath("//*[@onclick='editUser()']");

    public void openProfile() {
        driver.findElement(profileButton).click();
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterBio(String bio) {
        driver.findElement(bioInput).sendKeys(bio);
    }

    public void enterPhoneNum(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickOnSave() {
        driver.findElement(saveButton).click();
    }

    public boolean getUpdatedCookieData(String name, String bio, String phone) {
        int tempcounter = 0;
        String s = driver.manage().getCookieNamed("Testaccount").getValue();

        if (s.contains(name)) {
            tempcounter++;
        }
        if (s.contains(bio)) {
            tempcounter++;
        }
        if (s.contains(phone)){
            tempcounter++;
        }
        if (tempcounter >= 3){
            return true;
        }
        return false;
    }
}


