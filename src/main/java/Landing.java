import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Landing extends PageBase {

    public Landing(WebDriver driver) {
        super(driver);
    }

    private final By imglist = By.xpath("//img");

    public void navigateToSpecifiedUrl(String specifiedUrl) {
        driver.navigate().to(specifiedUrl);
    }

    public int siteStatusCode(String url) {
        RestAssured.baseURI = url;
        return RestAssured.given().get().getStatusCode();
    }

    public void saveAllImage() throws IOException {
        List<WebElement> imageList = driver.findElements(imglist);

        File storageDirectory = new File("SavedImages");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                throw new IOException("Error creating directory.");
            }
        }

        int imgcount = 0;
        for (WebElement element : imageList) {
            String src = element.getAttribute("src");
            System.out.println(src);

            String[] arr = src.split("/");
            String filename = arr[arr.length - 1];

            if (!src.contains(".svg")) {
                URL imageURL = new URL(src);

                //read image from url
                BufferedImage savedImage = ImageIO.read(imageURL);

                //writing the image to the disk
                imgcount++;

                ImageIO.write(savedImage, "png", new File("./" + storageDirectory + "/" + filename));
            }
        }
    }

    public boolean getAllFilesFromFolder() {
        File folder = new File("./SavedImages");
        File[] listOfFiles = folder.listFiles();
        List<String> filenames = new ArrayList<>();
        int counter = 0;

        for (File file : listOfFiles) {
            if (file.isFile()) {
                filenames.add(file.getName());
            }
        }
        List<WebElement> imageList = driver.findElements(imglist);

        for (int i = 0; i < filenames.size(); i++) {
            for (WebElement element : imageList) {
                String src = element.getAttribute("src");
                if (src.contains(filenames.get(i))) {
                    counter++;
                    break;
                }
            }
        }
        if (counter == filenames.size()) {
            return true;
        }
        return false;
    }


}