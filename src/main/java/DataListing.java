import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataListing extends PageBase {

    public DataListing(WebDriver driver) {
        super(driver);
    }

    private By listItems = By.xpath("//*[@class='resume__education_item']/h4");
    private By experienceButton = By.xpath("//a[@href='#experience']");

    public String[] allWorkToList()
    {
        List<WebElement> workPlaces = driver.findElements(listItems);
        String[] workArray = new String[workPlaces.size()];
        int j = 0;

        while (true){
            for (int i = j ; i < workPlaces.size(); i++) {
                String name = workPlaces.get(i).getText();
                workArray[i] = name;
                if (!workPlaces.get(i).getText().equals("")) {
                    j = i+1;
                }
            }
            if (driver.findElement(experienceButton).getAttribute("class").contains("active")) {
                break;
            }
            driver.findElement(experienceButton).click();
        }

        return workArray;
    }
}
