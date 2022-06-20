import org.openqa.selenium.WebDriver;

public class PageFactory {
    public static PageBase Create(String name, WebDriver driver) {
        switch (name) {
            case "Landing":
                return new Landing(driver);
            case "Privacy":
                return new Privacy(driver);
            case "Register":
                return new Register(driver);
            case "Login":
                return new Login(driver);
            case "BlogListing":
                return new BlogListing(driver);
            case "LogOut":
                return new LogOut(driver);
            case "DeleteDatas":
                return new DeleteDatas(driver);
            case "ModifyData":
                return new ModifyData(driver);
            case "DataListing":
                return new DataListing(driver);
            default:
                return null;
        }
    }
}
