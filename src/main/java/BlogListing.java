import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogListing extends PageBase {

    public BlogListing(WebDriver driver) {
        super(driver);
    }

    private final By blogMenuLink = By.xpath("//a[contains(text(),'Blog')]");
    private final By blogPageLink = By.xpath("//a[contains(text(),'See all post')]");
    private final By nextbutton = By.xpath("//a[@rel='next']");

    public void selectBlogInTheMenu() {
        driver.findElement(blogMenuLink).click();
    }

    public void goToBlogPage() {
        driver.findElement(blogPageLink).click();
    }

    public Boolean isLastPage() {
        try{
            driver.findElement(nextbutton);
            return false;
        } catch (Exception e){
            return true;
        }
    }

    public void toTheNextPage() {
            driver.findElement(nextbutton).click();
    }
}
