import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeleteDatas extends PageBase {

    public DeleteDatas(WebDriver driver) {
        super(driver);
    }

    private By profileButton = By.id("profile-btn");
    private By deleteButton = By.xpath("//*[@onclick='showRealDeleteAccBtn()']");
    private By realDeleteButton = By.id("delete-account-btn");

    public int getAllCookiesNum() {
        int totalUser = 0;
        try {
            // Get All available cookies
            Set<Cookie> cookies = driver.manage().getCookies();

            ArrayList<Cookie> cookiesToList = new ArrayList<Cookie>(cookies);

            totalUser = cookiesToList.size();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return totalUser;
    }

    public void clickOnProfileButton() {
        driver.findElement(profileButton).click();
    }

    public void clickOnDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public void clickOnRealButton() {
        driver.findElement(realDeleteButton).click();
    }
}


