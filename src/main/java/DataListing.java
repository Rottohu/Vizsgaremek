import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataListing extends PageBase {

    public DataListing(WebDriver driver) {
        super(driver);
    }

    private By listItems = By.xpath("//*[@class='resume__education_item']/h4");

    public String[] allWorkToList()
    {
        List<WebElement> workPlaces = driver.findElements(listItems);
        String[] workArray = new String[workPlaces.size()];
        for (int i = 0; i < workPlaces.size(); i++) {
            String name = workPlaces.get(i).getText();
            workArray[i] = name;
        }
        return workArray;
    }
}
