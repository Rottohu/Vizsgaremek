import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebpageTests {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        //options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @Tag("SiteOnline")
    @DisplayName("Test if the site is online")
    @Test
    public void navigateToSite() {
        Landing landing = (Landing) PageFactory.Create("Landing", driver);
        landing.navigateToUrl();
        String actual = driver.getCurrentUrl();
        Allure.addAttachment("isOnline", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("https://lennertamas.github.io/portio/", actual);
    }

    @Tag("SiteOnline")
    @DisplayName("Test if the site is online with Rest")
    @Test
    public void siteStatusCodeTest() {
        Landing landing = (Landing) PageFactory.Create("Landing", driver);
        int actual = landing.siteStatusCode("https://lennertamas.github.io/portio/");
        Allure.addAttachment("isOnline", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals(200, actual);
    }

    @Tag("Terms")
    @DisplayName("Test if I can navigate to the Landing Page without logging in")
    @Test
    public void navigateToLandingPage() throws InterruptedException {
        Landing landing = (Landing) PageFactory.Create("Landing", driver);
        landing.navigateToSpecifiedUrl("https://lennertamas.github.io/portio/landing.html");
        Thread.sleep(1500);
        String actual = driver.getCurrentUrl();
        Allure.addAttachment("test", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("https://lennertamas.github.io/portio/landing.html", actual);
    }


    @Tag("Terms")
    @DisplayName("Test if I click the X button the Terms popup disappears")
    @Test
    public void termsExitWithXButton() throws InterruptedException {
        Privacy privacy = (Privacy) PageFactory.Create("Privacy", driver);
        privacy.navigateToUrl();
        Thread.sleep(1500);
        privacy.clickOnX();
        Thread.sleep(1000);

        String displayStyle = privacy.getDisplayStyle();
        Allure.addAttachment("PopupwithX", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("none", displayStyle);
    }

    @Tag("Terms")
    @DisplayName("Test if I click on the Accept button the Terms popup disappears")
    @Test
    public void termsAcceptButton() throws InterruptedException {
        Privacy privacy = (Privacy) PageFactory.Create("Privacy", driver);
        privacy.navigateToUrl();
        Thread.sleep(1500);
        privacy.clickOnAccept();
        Thread.sleep(1000);

        String displayStyle = privacy.getDisplayStyle();
        Allure.addAttachment("PopupWithAccept", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("none", displayStyle);
    }

    @Tag("Terms")
    @DisplayName("Test if I click on the outside of the Terms popup and it still displayed")
    @Test
    public void termsStillDisplayed() throws InterruptedException {
        Privacy privacy = (Privacy) PageFactory.Create("Privacy", driver);
        privacy.navigateToUrl();
        Thread.sleep(1500);

        try {
            privacy.clickOnNowhere();
        } catch (Exception ignored) {
        }

        Thread.sleep(1000);

        String displayStyle = privacy.getDisplayStyle();
        Allure.addAttachment("ClickOnBody", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("block", displayStyle);
    }

    @Tag("Terms")
    @DisplayName("Test if I reload the page, the popup window still exists")
    @Test
    public void termsWithReloadPage() throws InterruptedException {
        Privacy privacy = (Privacy) PageFactory.Create("Privacy", driver);
        privacy.navigateToUrl();
        Thread.sleep(1500);
        driver.navigate().refresh();
        Thread.sleep(1000);

        String displayStyle = privacy.getDisplayStyle();
        Allure.addAttachment("afterReload", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("block", displayStyle);
    }


    @Tag("Registration")
    @DisplayName("Registration test with valid datas")
    @Test
    public void fullRegisterTest() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("Test");
        register.sendPassword("Testpassword");
        register.sendEmailAddress("test@test.com");
        register.sendDescription("Example description");
        Allure.addAttachment("filledwithDatas", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("validRegisterScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test without any data")
    @Test
    public void registerWithoutAnyData() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("");
        register.sendPassword("");
        register.sendEmailAddress("");
        register.sendDescription("");
        Allure.addAttachment("empty", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("User registered!", actual);
    }


    @Tag("Registration")
    @DisplayName("Registration test with only username")
    @Test
    public void registerWithOnlyUsername() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("TestUser");
        register.sendPassword("");
        register.sendEmailAddress("");
        register.sendDescription("");
        Allure.addAttachment("withUserName", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test with only password")
    @Test
    public void registerWithOnlyPassword() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("");
        register.sendPassword("TestPassword");
        register.sendEmailAddress("");
        register.sendDescription("");
        Allure.addAttachment("withPassword", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test with only emailaddress")
    @Test
    public void registerWithOnlyEmail() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("");
        register.sendPassword("");
        register.sendEmailAddress("test@test.hu");
        register.sendDescription("");
        Allure.addAttachment("withEmail", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test with only Description")
    @Test
    public void registerWithOnlyDescription() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("");
        register.sendPassword("");
        register.sendEmailAddress("");
        register.sendDescription("Description");
        Allure.addAttachment("withDesc", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertNotEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test with only Username & Password filled")
    @Test
    public void registerWithUserAndPass() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("TestUser");
        register.sendPassword("TestPassword");
        register.sendEmailAddress("");
        register.sendDescription("");
        Allure.addAttachment("withUserandPass", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        //The site needs the user email address if there's a login for different reason. For example: The user request to delete all their data from the site
        Assertions.assertNotEquals("User registered!", actual);
    }

    @Tag("Registration")
    @DisplayName("Registration test with only Username,Password & Email fields are filled")
    @Test
    public void registerWithBasicData() throws InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        Thread.sleep(1500);
        register.selectRegister();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.sendUserName("TestUser");
        register.sendPassword("TestPassword");
        register.sendEmailAddress("test@test.com");
        register.sendDescription("");
        Allure.addAttachment("withUserPassEmail", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();

        String actual = register.showRegMessage();
        Allure.addAttachment("registered", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals("User registered!", actual);
    }


    @Tag("Login")
    @DisplayName("Login with registrated user")
    @Test
    public void succesfulLoginTest() throws InterruptedException {
        Login login = (Login) PageFactory.Create("Login", driver);
        fullRegisterTest();
        Allure.addAttachment("registerScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.clickLoginTab();
        Allure.addAttachment("loginScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.sendUsername("Test");
        login.sendPassword("Testpassword");
        Allure.addAttachment("loginFilled", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.pushLoginButton();
        Thread.sleep(1500);

        String actual = driver.getCurrentUrl();
        Allure.addAttachment("success", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals(login.getLoggedInUrl(), actual);
    }


    @Tag("Login")
    @DisplayName("Login before register a user")
    @Test
    public void loginWithoutRegTest() throws InterruptedException {
        Login login = (Login) PageFactory.Create("Login", driver);
        login.navigateToUrl();
        login.popupKiller();
        Allure.addAttachment("loginScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.sendUsername("Test");
        login.sendPassword("Testpassword");
        Allure.addAttachment("loginFilled", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.pushLoginButton();
        Thread.sleep(1500);

        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();
        Allure.addAttachment("result", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals(expected, actual);
    }

    @Tag("Login")
    @DisplayName("Login with only username registered")
    @Test
    public void loginWithOnlyUsernameTest() throws InterruptedException {
        Login login = (Login) PageFactory.Create("Login", driver);
        Register register = (Register) PageFactory.Create("Register", driver);
        login.navigateToUrl();
        login.popupKiller();
        register.selectRegister();
        register.sendUserName("TestUser");
        Allure.addAttachment("register", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();
        login.clickLoginTab();
        Allure.addAttachment("loginScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.sendUsername("TestUser");
        Allure.addAttachment("loginuser", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.pushLoginButton();
        Thread.sleep(1500);

        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();
        Allure.addAttachment("result", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals(expected, actual);
    }

    @Tag("Login")
    @DisplayName("Login with only password registered")
    @Test
    public void loginWithOnlyPasswordTest() throws InterruptedException {
        Login login = (Login) PageFactory.Create("Login", driver);
        Register register = (Register) PageFactory.Create("Register", driver);
        login.navigateToUrl();
        login.popupKiller();
        register.selectRegister();
        register.sendPassword("TestPass");
        Allure.addAttachment("register", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        register.pushTheRegButton();
        login.clickLoginTab();
        Allure.addAttachment("loginScreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.sendPassword("Testpass");
        Allure.addAttachment("loginpass", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        login.pushLoginButton();
        Thread.sleep(1500);

        String expected = "https://lennertamas.github.io/portio/";
        String actual = driver.getCurrentUrl();
        Allure.addAttachment("result", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertEquals(expected, actual);
    }

    @Tag("Login")
    @DisplayName("Login with only username & password registered")
    @Test
    public void loginWithUsernameAndPass() throws InterruptedException {
        Login login = (Login) PageFactory.Create("Login", driver);
        login.fullLogin();
        Thread.sleep(1500);

        String actual = driver.getCurrentUrl();

        Assertions.assertEquals(login.getLoggedInUrl(), actual);
    }

    @Tag("DataSaving")
    @Description("Download all image to a newly created folder from the landing page")
    @Test
    public void fileDownload() throws InterruptedException, IOException {
        Landing landing = (Landing) PageFactory.Create("Landing", driver);
        landing.fullLogin();
        Thread.sleep(2000);
        landing.saveAllImage();
        boolean expected = landing.getAllFilesFromFolder();

        Assertions.assertTrue(expected);
    }

    @Tag("RepeatedDataFromFile")
    @Description("Register multiple user")
    @Test
    public void registerMultipleUser() throws IOException, ParseException, InterruptedException {
        Register register = (Register) PageFactory.Create("Register", driver);
        register.navigateToUrl();
        register.popupKiller();
        register.selectRegister();

        JSONArray users = register.jsonparser("registeredusers.json");
        for (int i = 0; i < users.size(); i++) {
            JSONObject jsonObject = (JSONObject) users.get(i);

            register.sendUserName((String) jsonObject.get("username"));
            register.sendPassword((String) jsonObject.get("password"));
            register.sendEmailAddress((String) jsonObject.get("email"));
            register.sendDescription((String) jsonObject.get("text"));

            register.pushTheRegButton();

            String actual = register.showRegMessage();

            Thread.sleep(1500);

            Assertions.assertEquals("User registered!", actual);

            register.inputFieldClear();
            Thread.sleep(1500);
        }
    }

    @Tag("MultiplePageList")
    @Description("List the blogs")
    @Test
    public void blogListingTest() throws InterruptedException {
        BlogListing bl = (BlogListing) PageFactory.Create("BlogListing", driver);
        bl.fullLogin();
        bl.selectBlogInTheMenu();
        Thread.sleep(1500);
        bl.goToBlogPage();
        Allure.addAttachment("blogscreen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Thread.sleep(1500);

        boolean actual = false;

        while (true) {
            if (bl.isLastPage()) {
                actual = true;
                break;
            } else {
                bl.toTheNextPage();

            }
        }
        Allure.addAttachment("lastpage", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertTrue(actual);
    }

    @Tag("LogOut")
    @Description("Simple Register, Login, Logut process")
    @Test
    public void normalLogOutTest() throws InterruptedException {
        LogOut logout = (LogOut) PageFactory.Create("LogOut", driver);
        logout.fullLogin();
        Allure.addAttachment("loggedIn", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        logout.clickOnLogout();
        Allure.addAttachment("loggedOut", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        boolean actual = logout.isLoggedOut();

        Assertions.assertTrue(actual);
    }

    @Tag("DeleteData")
    @Description("Register a user, checks how many cookies on the site, delete user, checks again the number. If it smaller, it works.")
    @Test
    public void deleteAllUserData() throws InterruptedException {
        DeleteDatas dd = (DeleteDatas) PageFactory.Create("DeleteDatas", driver);
        dd.fullLogin();

        int beforeDelete = dd.getAllCookiesNum();

        dd.clickOnProfileButton();
        Allure.addAttachment("profile", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        dd.popupKiller();
        Allure.addAttachment("deleteButton", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        dd.clickOnDeleteButton();
        Allure.addAttachment("confirmDeleteButton", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        dd.clickOnRealButton();

        int afterDelete = dd.getAllCookiesNum();

        Assertions.assertNotEquals(beforeDelete, afterDelete);
    }

    @Tag("ModifyData")
    @Tag("NewDataInsert")
    @Description("Update the profile, and check the updated cookie, if it contains the updated datas")
    @Test
    public void modifyDataTest() throws InterruptedException {
        ModifyData md = (ModifyData) PageFactory.Create("ModifyData",driver);
        String name = "Rotto";
        String bio = "Catlover";
        String phone = "06123456789";
        md.fullLogin();
        md.openProfile();
        Allure.addAttachment("profile", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        md.enterName(name);
        md.enterBio(bio);
        md.enterPhoneNum(phone);
        md.popupKiller();
        Allure.addAttachment("filled", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        md.clickOnSave();
        Allure.addAttachment("saved", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(md.getUpdatedCookieData(name,bio,phone));

    }

    @Tag("DataListing")
    @Description("Listing all the schools & work experiences")
    @Test
    public void dataListingTest() throws InterruptedException {
        DataListing dataListing = new DataListing(driver);
        dataListing.fullLogin();

        String[] expected = {"Master in Arts","Master in Arts","Master in Arts","Master in Arts","Umbrella co.","Aperture Science","ACME Inc.","LexCorp"};

        Allure.addAttachment("beforeScreenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        String[] actual = dataListing.allWorkToList();
        Allure.addAttachment("afterScreenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertArrayEquals(expected,actual);

    }

    @AfterEach
    public void NukeIt() {
        driver.quit();
    }
}