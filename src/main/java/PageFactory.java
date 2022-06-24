import org.openqa.selenium.WebDriver;

public class PageFactory {
    public static PageBase Create(String name, WebDriver driver) {
        return switch (name) {
            case "Landing" -> new Landing(driver);
            case "Privacy" -> new Privacy(driver);
            case "Register" -> new Register(driver);
            case "Login" -> new Login(driver);
            case "BlogListing" -> new BlogListing(driver);
            case "LogOut" -> new LogOut(driver);
            case "DeleteDatas" -> new DeleteDatas(driver);
            case "ModifyData" -> new ModifyData(driver);
            case "DataListing" -> new DataListing(driver);
            default -> null;
        };
    }
}
