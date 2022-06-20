import java.io.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Register extends PageBase {
    public Register(WebDriver driver) {
        super(driver);
    }

    private final By registerTab = By.xpath("(//*[@id=\"register-form-button\"])[1]");
    private final By regUsername = By.xpath("//*[@id=\"register-username\"]");
    private final By regPassword = By.xpath("//*[@id=\"register-password\"]");
    private final By regEmail = By.xpath("//*[@id=\"register-email\"]");
    private final By regDescription = By.xpath("//*[@id=\"register-description\"]");
    private final By regButton = By.xpath("//*[@onclick=\"registerUser()\"]");
    private final By message = By.xpath("//*[@id=\"register-alert\"]");

    public void selectRegister() {
        driver.findElement(registerTab).click();
    }

    public void sendUserName(String username) {
        driver.findElement(regUsername).sendKeys(username);
    }

    public void sendPassword(String password) {
        driver.findElement(regPassword).sendKeys(password);
    }

    public void sendEmailAddress(String emailaddress) {
        driver.findElement(regEmail).sendKeys(emailaddress);
    }

    public void sendDescription(String description) {
        driver.findElement(regDescription).sendKeys(description);
    }

    public void pushTheRegButton() {
        driver.findElement(regButton).click();
    }

    public String showRegMessage() {
        return driver.findElement(message).getText();
    }

    public void registerMultipleUsers() {
        try {
            File myObj = new File("users.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public JSONArray jsonparser(String file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonarray = (JSONArray) parser.parse(new FileReader(file));
        System.out.println("jsonarray size = " + jsonarray.size());
        return jsonarray;
    }

    public void inputFieldClear()
    {
        driver.findElement(regUsername).clear();
        driver.findElement(regPassword).clear();
        driver.findElement(regEmail).clear();
        driver.findElement(regDescription).clear();
    }
}

